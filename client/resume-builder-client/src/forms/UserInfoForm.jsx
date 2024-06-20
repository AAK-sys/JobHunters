import {useState} from 'react'

function UserInfoForm() { //{ information, onChange }
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        phone: '',
        url: '',
        location: ''
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
            <form className="p-3 pt-4 bg-white rounded-md">
                <h2 className="text-xl font-semibold text-gray-900 mb-4">Personal Information</h2>
                <div className="flex flex-col gap-3">
                    <div className="flex flex-col gap-1">
                        <label className="text-base font-medium text-gray-700">Name</label>
                        <input 
                            type="text" 
                            name="name" 
                            value={formData.name} 
                            onChange={handleChange}
                            className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                        />
                    </div>
                    <div className="grid grid-cols-2 gap-3">
                        <div className="flex flex-col gap-1">
                            <label className="text-base font-medium text-gray-700">Email</label>
                            <input 
                                type="text" 
                                name="email" 
                                value={formData.email} 
                                onChange={handleChange}
                                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                            />
                        </div>
                        <div className="flex flex-col gap-1">
                            <label className="text-base font-medium text-gray-700">Phone</label>
                            <input 
                                type="text" 
                                name="phone" 
                                value={formData.phone} 
                                onChange={handleChange}
                                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                            />
                        </div>
                    </div>
                    <div className="grid grid-cols-2 gap-3 mb-3">
                        <div className="flex flex-col gap-1">
                            <label className="text-base font-medium text-gray-700">Website</label>
                            <input 
                                type="text" 
                                placeholder="linkedin.com/in/username" 
                                name="url" 
                                value={formData.url}
                                onChange={handleChange}
                                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                            />
                        </div>
                        <div className="flex flex-col gap-1">
                            <label className="text-base font-medium text-gray-700">Location</label>
                            <input 
                                type="text" 
                                name="location" 
                                value={formData.location} 
                                onChange={handleChange}
                                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                            />
                        </div>
                    </div>
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

  );
}

export default UserInfoForm