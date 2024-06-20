import {useState} from 'react'
import { jwtDecode } from "jwt-decode";

function EducationForm({formData, handleChange}) {
    const [prepareForChange, setPrepareForChange] = useState(false);

    const buttonClass = !prepareForChange 
    ? "transition ease-in duration-1000 text-black bg-gray-500 px-4 py-2 rounded-md" 
    : "transition ease-in duration-1000 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600";

    const submitRequest = (event) =>{
        event.preventDefault();
        console.log(formData.educationId === 0 ? "Add" : "edit", formData);
    }

    const registerChange = (event) =>{
        handleChange(event);
        setPrepareForChange(true);
    }

    return (
        <div className='w-auto p-2'>
        <form className="bg-white rounded-md" onSubmit={submitRequest}>
            <div className="mb-4">
                <label htmlFor="institution" className="block text-sm font-medium text-gray-700 mb-1">Institution</label>
                <input 
                    type="text" 
                    id="institution" 
                    name="institution" 
                    value={formData.universityName} 
                    onChange={registerChange} 
                    placeholder="Enter Institution" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                />
            </div>

            
            <div className="flex flex-wrap gap-4 mb-4">
                <div className="flex-1">
                    <label htmlFor="degree" className="block text-sm font-medium text-gray-700 mb-1">Start Date</label>
                    <input 
                    type="text" 
                    id="degree" 
                    name="degree" 
                    value={formData.degree /*maybe a drop down later*/} 
                    onChange={registerChange} 
                    placeholder="B.S." 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>
                <div className="flex-1">
                    <label htmlFor="major" className="block text-sm font-medium text-gray-700 mb-1">End Date (if applicable)</label>
                    <input 
                    type="text" 
                    id="major" 
                    name="major" 
                    value={formData.major} 
                    onChange={registerChange} 
                    placeholder="Computer Science" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>
            </div>

            <div className="mb-4">
                <label htmlFor="gpa" className="block text-sm font-medium text-gray-700 mb-1">Company Name</label>
                <input 
                    type="number"
                    step="0.01" 
                    id="gpa" 
                    name="gpa" 
                    value={formData.gpa} 
                    onChange={registerChange} 
                    placeholder="4.0" 
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
                    onChange={registerChange} 
                    placeholder="01/01/2000" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>
                <div className="flex-1">
                    <label htmlFor="major" className="block text-sm font-medium text-gray-700 mb-1">End Date (if applicable)</label>
                    <input 
                    type="text" 
                    id="major" 
                    name="major" 
                    value={formData.endDate} 
                    onChange={registerChange} 
                    placeholder="01/01/2004" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>
            </div>

            <div className="mb-4">
                <label htmlFor="description" className="block text-sm font-medium text-gray-700 mb-1">Description</label>
                <textarea 
                    id="description" 
                    name="description" 
                    value={formData.description === null ? "" : formData.description} 
                    onChange={registerChange} 
                    placeholder="Describe your role and responsibilities"
                    rows="3" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 resize-none"
                />
            </div>

            <div className={formData.educationId !== 0 ? "flex justify-between" : "flex justify-end"}>

            {(formData.educationId && <button 
                    type="submit" 
                    className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:bg-red-600"
                >
                    Delete
                </button>) || ''}
                <button 
                    type="submit" 
                    className={buttonClass}
                >
                    {formData.role === '' ? "add new education" : "confirm changes"}
                </button>
            </div>
        </form>
        </div>
    )
}

export default EducationForm