import React, { useState } from "react";
import DropDown from "./DropDown";

const DynamicForm = ({
    information,
    setInformation,
    options,
    setOptions,
    FormComponent,
    title,
}) => {
    const [selectedOption, setSelectedOption] = useState(null); // State to hold selected option

    const handleChange = (selectedOption) => {
        setSelectedOption(selectedOption); // Update selected option
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setInformation({
            ...information,
            [selectedOption.value]: {
                ...information[selectedOption.value],
                [name]: value,
            },
        });
    };

    return (
        <div className="p-2 m-3 rounded-lg shadow">
            <div className="flex justify-between mb-2 pt-2">
                <h2 className="text-xl font-semibold text-gray-700 mr-3">
                    {title}
                </h2>
                <DropDown options={options} onSelect={handleChange} selectedOption={selectedOption} />
            </div>
            <div className="mt-4">
                <div className="rounded-xl text-lg bg-white">
                    {selectedOption && (
                        <FormComponent
                            formData={information[selectedOption.value]}
                            handleChange={handleInputChange}
                            options={options}
                            setOptions={setOptions}
                            setInformation = {setInformation}
                            information = {information}
                            setSelected={setSelectedOption}
                            onSelectChange={handleChange}
                        />
                    )}
                </div>
            </div>
        </div>
    );
};

export default DynamicForm;
