import { useState } from 'react'

function ExperienceForm() {
    const [formData, setFormData] = useState({
        companyName: '',
        role: '',
        displayName: '',
        startDate: '',
        endDate: '',
        description: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    return (
        <div className='w-auto p-4'>
        <form className="p-6 pt-4 bg-white rounded-md shadow">
            <h2 className="text-xl font-semibold text-gray-900 mb-6">Experience</h2>

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

            <div className="grid grid-cols-2 gap-4 mb-4">
                <div>
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
                <div>
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
                    value={formData.description} 
                    onChange={handleChange} 
                    placeholder="Describe your role and responsibilities"
                    rows="3" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 resize-none"
                />
            </div>

            <div className="flex justify-end">
                <button 
                    type="submit" 
                    className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600"
                >
                    Save
                </button>
            </div>
        </form>
        </div>
    )
}

export default ExperienceForm