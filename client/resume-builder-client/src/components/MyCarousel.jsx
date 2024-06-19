import React from "react";
import Carousel from "react-multi-carousel";
import CarouselItem from "./CarouselItem";
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

    const resumeTemplates = ["Default Template", "Coming Soon"];
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
            className="my-8 pb-10"
        >
            {resumeTemplates.map((message, i) => {
                return <CarouselItem key={i} message={message} img={""} />;
            })}
        </Carousel>
    );
}

export default MyCarousel;
