import React from 'react';
import Select from 'react-select';

const DropDown = ({ options, onSelect  }) => {

    const handleChange = (selectedOption) => {
        onSelect(selectedOption); // Call onSelect function with selected option
    };

  return (
    <div className="w-64 mx-auto mt-4">
      <Select
        options={options}
        onChange={onSelect}
        isSearchable={true}
        placeholder="Select an option"
      />
    </div>
  );
};

export default DropDown;