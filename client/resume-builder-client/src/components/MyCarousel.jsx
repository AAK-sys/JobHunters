import React from "react";
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";

function MyCarousel() {
    const responsive = {
        desktop: {
            breakpoint: { max: 3000, min: 1024 },
            items: 3,
            slidesToSlide: 3, // optional, default to 1.
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

    const text = ["test 1", "test 2", "test 3", "test 4", "test 5"];
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
        >
            {text.map((message, i) => {
                return (
                    <div className="mx-20 p-8 overflow-hidden" key={i}>
                        {message}
                    </div>
                );
            })}
        </Carousel>
    );
}

export default MyCarousel;
