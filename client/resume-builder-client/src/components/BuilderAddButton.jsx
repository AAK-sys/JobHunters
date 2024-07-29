import React from "react";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { cn } from "../utils/cn";

function BuilderButton({ className, icon, text }) {
    return (
        <Link
            className={cn(
                "flex gap-2 items-center border border-black rounded-lg p-2 w-max mt-2 ml-auto",
                className
            )}
            to="/user"
        >
            <FontAwesomeIcon icon={icon} />
            <span>{text}</span>
        </Link>
    );
}

export default BuilderButton;
