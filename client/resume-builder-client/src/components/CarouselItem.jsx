import React from "react";
import { Link } from "react-router-dom";
import { cn } from "../utils/cn";

function CarouselItem({ img, message, className }) {
    return (
        <Link to="/create" className="block border rounded-md mx-24 w-max p-4">
            <img
                src={img}
                alt="Resume"
                className={cn("w-[200px] h-[250px] mb-4", className)}
            />
            <p className="p-2 italic text-center bg-green-50">{message}</p>
        </Link>
    );
}

export default CarouselItem;
