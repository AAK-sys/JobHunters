import React from 'react';

const SampleForm1 = ({ information, onChange }) => {

    const handelSubmit = (event) => {
        event.preventDefault();
    }

    return (
        <form className="bg-transparent" onSubmit={handelSubmit}>
          <div className="mb-4">
            <label htmlFor="name" className="text-gray-700">Name:</label>
            <input 
              type="text" 
              id="name" 
              name="name" 
              value={information.name} 
              onChange={onChange} 
              className="border border-gray-300 rounded px-3 py-2 w-full bg-transparent text-gray-900 placeholder-gray-400 focus:outline-none focus:border-blue-500" 
            />
          </div>
          <div className="mb-4">
            <label htmlFor="email" className="text-gray-700">Email:</label>
            <input 
              type="email" 
              id="email" 
              name="email" 
              value={information.email} 
              onChange={onChange} 
              className="border border-gray-300 rounded px-3 py-2 w-full bg-transparent text-gray-900 placeholder-gray-400 focus:outline-none focus:border-blue-500" 
            />
          </div>
          <button type="submit" className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded shadow focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">
            Submit
          </button>
        </form>
      );
};

export default SampleForm1;