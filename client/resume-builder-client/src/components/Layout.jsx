import React from "react";
import NavBar from "./NavBar";
import { cn } from "../utils/cn";

function Layout({ children, className }) {
    return (
        <>
            <NavBar />
            <div className={cn(className, "m-auto flex h-[calc(100vh-4rem)]")}>
                {children}
            </div>
        </>
    );
}

export default Layout;
