import {useState} from 'react'

function UserInfoForm({formData, setFormData}) {

    const [prepareForChange, setPrepareForChange] = useState(false);

    const EXP_URL = `http://localhost:8080/api/userinfo/${formData.userInfoId}`;

    const buttonClass = !prepareForChange 
    ? "transition ease-in duration-1000 text-black bg-gray-500 px-4 py-2 rounded-md" 
    : "transition ease-in duration-1000 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600";

    const handelUpdate = (e) => {
        
        e.preventDefault();

        const token = localStorage.getItem("jwtToken");
        const options = {
            method: "PUT",
            headers: {
                Authorization: "Bearer " + token,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        };

        console.log(formData);

        fetch(`${EXP_URL}`, options)
            .then((res) => {
                
                if(res.status == 201 || res.status == 204){
                    alert(`your data has been ${res.status == 201 ? "Added" : "updated"}`)
                }

                return res.json();

            }).then((data)=>{
                if(data){
                    alert(data);
                }
            }).catch((e) => {
                //alert(e);
            })

    }

    const handleChange = (e) => {
        setPrepareForChange(true);
        const { name, value } = e.target;
        console.log(name, value);
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
    };

    const handelSubmit = (event) => {
        event.preventDefault();
    }

    return (
        <div className='w-[45%] m-3 shadow rounded-lg p-4'>
            <form className="p-3 pt-4 bg-white rounded-md" onSubmit={handelSubmit}>
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
                    onClick={handelUpdate}
                    type="submit" 
                    className={buttonClass}
                >
                    Save
                </button>
            </div>
            </form>
        </div>

  );
}

export default UserInfoForm