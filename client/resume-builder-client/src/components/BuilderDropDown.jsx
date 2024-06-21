import React from "react";
import Select from "react-select";

const BuilderDropDown = ({
    objValue,
    handleChange,
    objArray,
    label,
    idString,
}) => {
    return (
        <Select
            className="w-full"
            value={objValue}
            onChange={handleChange}
            options={objArray.map((item, i) => {
                return {
                    label: item[label],
                    value: item,
                    key: `${item[idString]}${i}`,
                };
            })}
            isSearchable={true}
            placeholder="Select an option"
            isMulti
        />
    );
};

export default BuilderDropDown;
