import React from "react";
import { Link } from "react-router-dom";

function CarouselItem({ img, message }) {
    return (
        <Link to="/create" className="block border rounded-md mx-28 w-max">
            <img
                src={img}
                alt="Resume"
                className="w-full h-full py-24 px-16 bg-yellow-100"
            />
            <p className="p-2 italic text-center bg-green-50">{message}</p>
        </Link>
    );
}

export default CarouselItem;
