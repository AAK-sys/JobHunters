import React from 'react';
import Select from 'react-select';

const DropDown = ({ options, onSelect  }) => {

  return (
    <div className="">
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