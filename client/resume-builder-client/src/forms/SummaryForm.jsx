import React from 'react'

function SummaryForm() {

  const submitRequest = () =>{}
  const handleChange = () =>{}

  return (
    <div className='w-[%45] p-2'>
    <form className="bg-white rounded-md" onSubmit={submitRequest}>
        <div className="mb-4">
            <label htmlFor="displayName" className="block text-sm font-medium text-gray-700 mb-1">Display Name</label>
            <input 
                type="text" 
                id="displayName" 
                name="displayName" 
                onChange={handleChange} 
                placeholder="Enter display name" 
                className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
            />
        </div>

        <div className="mb-4">
            <label htmlFor="description" className="block text-sm font-medium text-gray-700 mb-1">Description</label>
            <textarea 
                id="description" 
                name="description" 
                onChange={handleChange} 
                placeholder="Describe your role and responsibilities"
                rows="3" 
                className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 resize-none"
            />
        </div>

        <div className={"flex justify-end"}>

        <button 
                type="submit" 
                className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:bg-red-600"
            >
                Delete
            </button>
            <button 
                type="submit" 
                className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600"
            >
                add
            </button>
        </div>
    </form>
    </div>
  )
}

export default SummaryForm