import React from "react";
import { Link } from "react-router-dom";
import { Dropdown } from "react-bootstrap";

function SelectInfo({ items, name }) {
    return (
        <div>
            <h2>{name}</h2>
            <div className="w-full">
                {items ? (
                    <Dropdown>
                        <Dropdown.Toggle>Select {name}</Dropdown.Toggle>
                        <Dropdown.Menu>
                            {items.map((item) => {
                                return (
                                    <Dropdown.Item>
                                        {item.displayName}
                                    </Dropdown.Item>
                                );
                            })}
                        </Dropdown.Menu>
                    </Dropdown>
                ) : (
                    <>
                        <p>There are no {name} to add</p>
                        <Link
                            className="p-2 rounded-lg block w-max border hover:bg-slate-100"
                            to="/user"
                        >
                            Add {name}
                        </Link>
                    </>
                )}
            </div>
        </div>
    );
}

export default SelectInfo;
