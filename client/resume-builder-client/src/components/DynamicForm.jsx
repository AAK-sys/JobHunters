import React, { useState } from 'react';
import DropDown from './DropDown';

const DynamicForm = ({information, setInformation, options, FormComponent}) => {
  const [selectedOption, setSelectedOption] = useState(null); // State to hold selected option


  const handleChange = (selectedOption) => {
    setSelectedOption(selectedOption); // Update selected option
    console.log("from option changed");
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
    <div className="p-2 m-5 rounded-lg w-[45%] shadow">
      <div className="flex justify-between mb-4">
        <h2 className="text-xl font-semibold text-gray-700 mr-3">Form Title</h2>
        <DropDown options={options} onSelect={handleChange} />
      </div>
      <div className="mt-4">
      <div className="rounded-xl text-lg bg-white">
        {selectedOption && (
          <FormComponent
            formData={information[selectedOption.value]}
            setFormData={setInformation}
            handleChange={handleInputChange}
          />
          )}
        </div>
      </div>
  </div>
  );
}

export default DynamicForm;