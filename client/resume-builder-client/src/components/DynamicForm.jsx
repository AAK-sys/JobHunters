import React, { useState } from 'react';
import DropDown from './DropDown';
import SampleForm1 from '../forms/SampleForm1';

const DynamicForm = ({information, setInformation, options, FormComponent}) => {
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
    <div className="p-8 bg-gray-100 rounded-lg shadow-lg max-h-sm max-w-sm mx-auto">

      <DropDown options={options} onSelect={handleChange} />

      <div className="mt-4">
        <div className="shadow-lg rounded-xl p-8 text-lg bg-transparent">
          {selectedOption && (
            <FormComponent
              information={information[selectedOption.value]} 
              onChange={handleInputChange} 
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default DynamicForm;