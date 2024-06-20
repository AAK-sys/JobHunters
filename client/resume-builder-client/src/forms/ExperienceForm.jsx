import { useState } from 'react'

function ExperienceForm({formData, handleChange}) {

    const [prepareForChange, setPrepareForChange] = useState(false);

    const EXP_URL = formData.experienceId === 0 ? "http://localhost:8080/api/experience" : `http://localhost:8080/api/experience/${formData.experienceId}`

    const buttonClass = !prepareForChange 
    ? "transition ease-in duration-1000 text-black bg-gray-500 px-4 py-2 rounded-md" 
    : "transition ease-in duration-1000 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600";

    const addOrUpdate = () =>{
        const token = localStorage.getItem("jwtToken");
        const options = {
            method: formData.experienceId === 0 ? "POST" : "PUT",
            headers: {
                Authorization: "Bearer " + token,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        };

        fetch(`${EXP_URL}`, options)
            .then((res) => {
                const newOptions = [...options];
                if(res.status == 201){
                    alert(`your data has been Added`)
                }else if(res.status == 204){
                    alert(`your data has been Updated`)
                }

                return res.json();

            }).then((data)=>{
                if(data){
                    alert(data);
                }
            }).catch((e) => {
                alert(e);
            })
    }

    const deleteExp = () => {
        const token = localStorage.getItem("jwtToken");
        const options = {
            method: "Delete",
            headers: {
                Authorization: "Bearer " + token,
            },
        };

        fetch(`${EXP_URL}`, options)
            .then((res) => {
                
                if(res.status == 204){
                    alert(`your data has been deleted`)
                }

                return res.json();

            }).then((data)=>{
                if(data){
                    alert(data);
                }
            }).catch((e) => {
                //alert(e); will revist with time.
            })
    }

    const submitRequest = (event) =>{
        event.preventDefault();
        
        const name = event.target.name;

        if(name.includes("wildcard")){
            addOrUpdate();
        }else{
            deleteExp();
        }  
    }

    const registerChange = (event) =>{
        handleChange(event);
        setPrepareForChange(true);
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
                    onChange={registerChange} 
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
                    onChange={registerChange} 
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
                    onChange={registerChange} 
                    placeholder="Enter your role" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                />
            </div>

            <div className="flex flex-wrap gap-4 mb-4">
                <div className="flex-1">
                    <label htmlFor="startDate" className="block text-sm font-medium text-gray-700 mb-1">Start Date</label>
                    <input 
                    type="date" 
                    id="startDate" 
                    name="startDate" 
                    value={formData.startDate} 
                    onChange={registerChange} 
                    placeholder="MM/YYYY" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>
                <div className="flex-1">
                    <label htmlFor="endDate" className="block text-sm font-medium text-gray-700 mb-1">End Date (if applicable)</label>
                    <input 
                    type="date" 
                    id="endDate" 
                    name="endDate" 
                    value={formData.endDate} 
                    onChange={registerChange} 
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
                    onChange={registerChange} 
                    placeholder="Describe your role and responsibilities"
                    rows="3" 
                    className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 resize-none"
                />
            </div>

            <div className={formData.experienceId != 0 ? "flex justify-between" : "flex justify-end"}>

            {formData.experienceId && <button 
                    name="delete"
                    type="submit" 
                    onClick={deleteExp}
                    className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:bg-red-600"
                >
                    Delete
                </button> || ''}
                <button 
                    type="submit"
                    name="wildcard" 
                    onClick={addOrUpdate}
                    className={buttonClass}
                >
                    {formData.experienceId == 0 ? "add new experience" : "confirm changes"}
                </button>
            </div>
        </form>
        </div>
    )
}

export default ExperienceForm