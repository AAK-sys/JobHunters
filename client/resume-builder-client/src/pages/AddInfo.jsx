import React, { useState } from 'react';
import SampleForm1 from '../forms/SampleForm1';
import DynamicForm from '../components/DynamicForm';

const AddInfo = () => {

    const [information, setInformation] = useState({
      value1: { name: 'John', email: 'john@example.com' },
      value2: { name: 'Jane', email: 'jane@example.com' },
      value3: { name: 'Bob', email: 'bob@example.com' }
    });

    const options = [
      { value: 'value1', label: 'entry 1' },
      { value: 'value2', label: 'entry 2' },
      { value: 'value3', label: 'entry 3' },
    ];

    const form = SampleForm1
  
    return ( // add onSelect here so I can control the behavior
       <DynamicForm information={information} 
       setInformation={setInformation} 
       options={options} 
       FormComponent={form} 
       />
    );
  };
  
  export default AddInfo;