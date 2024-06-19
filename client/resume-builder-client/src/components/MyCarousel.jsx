import React from "react";
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";

function MyCarousel() {
    const responsive = {
        desktop: {
            breakpoint: { max: 3000, min: 1024 },
            items: 2,
            slidesToSlide: 2, // optional, default to 1.
        },
        tablet: {
            breakpoint: { max: 1024, min: 464 },
            items: 2,
            slidesToSlide: 2, // optional, default to 1.
        },
        mobile: {
            breakpoint: { max: 464, min: 0 },
            items: 1,
            slidesToSlide: 1, // optional, default to 1.
        },
    };

    const text = ["Default Template", "Coming Soon"];
    return (
        <Carousel
            responsive={responsive}
            swipeable={false}
            draggable={false}
            showDots={true}
            infinite={true}
            keyBoardControl={true}
            autoPlay={true}
            transitionDuration={500}
            partialVisbiles={true}
            dotListClass="custom-dot-list-styles"
            className="flex justify-between"
        >
            {text.map((message, i) => {
                return (
                    <div className="p-8 overflow-hidden" key={i}>
                        {message}
                    </div>
                );
            })}
        </Carousel>
    );
}

export default MyCarousel;
