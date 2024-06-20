import { useState } from 'react'

function ExperienceForm({formData, setFormData, handleChange}) {

    const submitRequest = (event) =>{
        event.preventDefault();
        console.log(formData.experienceId == 0 ? "Add" : "edit", formData);
    }

    return (
        <div className='w-auto p-2'>
        <form className="bg-white rounded-md" onSubmit={submitRequest}>
            <div className="mb-4">
                <label htmlFor="displayName" className="block text-sm font-medium text-gray-700 mb-1">Display Name</label>
                <input 
                    type="text" 
                    id="displayName" 
                    name="displayName" 
                    value={formData.displayName} 
                    onChange={handleChange} 
                    placeholder="Enter display name" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                />
            </div>

            <div className="mb-4">
                <label htmlFor="companyName" className="block text-sm font-medium text-gray-700 mb-1">Company Name</label>
                <input 
                    type="text" 
                    id="companyName" 
                    name="companyName" 
                    value={formData.companyName} 
                    onChange={handleChange} 
                    placeholder="Enter company name" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                />
            </div>

            <div className="mb-4">
                <label htmlFor="role" className="block text-sm font-medium text-gray-700 mb-1">Role</label>
                <input 
                    type="text" 
                    id="role" 
                    name="role" 
                    value={formData.role} 
                    onChange={handleChange} 
                    placeholder="Enter your role" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                />
            </div>

            <div className="flex flex-wrap gap-4 mb-4">
                <div className="flex-1">
                    <label htmlFor="startDate" className="block text-sm font-medium text-gray-700 mb-1">Start Date</label>
                    <input 
                    type="text" 
                    id="startDate" 
                    name="startDate" 
                    value={formData.startDate} 
                    onChange={handleChange} 
                    placeholder="MM/YYYY" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>
                <div className="flex-1">
                    <label htmlFor="endDate" className="block text-sm font-medium text-gray-700 mb-1">End Date (if applicable)</label>
                    <input 
                    type="text" 
                    id="endDate" 
                    name="endDate" 
                    value={formData.endDate} 
                    onChange={handleChange} 
                    placeholder="MM/YYYY" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>
            </div>


            <div className="mb-4">
                <label htmlFor="description" className="block text-sm font-medium text-gray-700 mb-1">Description</label>
                <textarea 
                    id="description" 
                    name="description" 
                    value={formData.description == null ? "" : formData.description} 
                    onChange={handleChange} 
                    placeholder="Describe your role and responsibilities"
                    rows="3" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 resize-none"
                />
            </div>

            <div className={formData.experienceId != 0 ? "flex justify-between" : "flex justify-end"}>

            {formData.experienceId && <button 
                    type="submit" 
                    className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:bg-red-600"
                >
                    Delete
                </button> || ''}
                <button 
                    type="submit" 
                    className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600"
                >
                    {formData.role == '' ? "add new experience" : "edit experience"}
                </button>
            </div>
        </form>
        </div>
    )
}

export default ExperienceForm