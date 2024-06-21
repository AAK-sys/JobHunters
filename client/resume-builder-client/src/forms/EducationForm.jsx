import { useState } from "react";
import { jwtDecode } from "jwt-decode";

function EducationForm({ formData, setFormData, handleChange, setOptions, setSelected, information, setInformation }) {
    
    const [prepareForChange, setPrepareForChange] = useState(false);

            const defaultEducation = {
            educationId: 0,
            universityName: "",
            degree: "",
            major: "",
            gpa: "",
            startDate: "",
            endDate: "",
            description: "",
            userId: formData.userId,
        };

    const buttonClass = !prepareForChange
        ? "transition ease-in duration-1000 text-black bg-gray-500 px-4 py-2 rounded-md"
        : "transition ease-in duration-1000 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600";

        const handelAdd =  () => {
        
            const url = "http://localhost:8080/api/education";
    
            const token = localStorage.getItem("jwtToken");
            const options = {
                method: "POST",
                headers: {
                    Authorization: "Bearer " + token,
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            };
    
            fetch(`${url}`, options)
                .then((res) => {
                    if (res.status === 201) {
                        alert(
                            `your data has been Added`
                        );
                    }
    
                    return res.json();
                })
                .then((data) => {
                    if(data && !data.educationId){
                        alert(data);
                    }else if(data){
                        setOptions(prevOptions => {
                            const newOption = { value: data.educationId, label: data.universityName };
                            const lastOption = prevOptions[prevOptions.length - 1];
                            return [...prevOptions.slice(0, -1), newOption, lastOption];
                        });
                        setInformation(prevInfo => {
                            const entries = Object.entries(prevInfo);
                            const lastEntry = entries.pop();
                            const newEntries = [...entries, [data.educationId, data], lastEntry];
                            return Object.fromEntries(newEntries);
                        })
                        const newInformation = information;
                        newInformation[0] = defaultEducation;
                        setFormData(defaultEducation);
                        setInformation(newInformation);
                    }
                })
                .catch((e) => {
                });
        };
    
        const handelUpdate = () => {

            const url = `http://localhost:8080/api/education/${formData.educationId}`
    
            const token = localStorage.getItem("jwtToken");
            const options = {
                method: "PUT",
                headers: {
                    Authorization: "Bearer " + token,
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            };
    
            fetch(url, options)
                .then((res) => {
                    if (res.status === 204) {
                        alert(
                            `your data has been ${
                                res.status === 201 ? "Added" : "updated"
                            }`
                        );
                    }
    
                    return res.json();
                })
                .then((data) => {
                    if (data) {
                        alert(data);
                    }
                })
                .catch((e) => {

                });
        };

    const registerChange = (event) => {
        handleChange(event);
        setPrepareForChange(true);
    };

    const handelSubmit = (e) => {

        e.preventDefault();

        if(formData.educationId === 0 ){
            handelAdd();
        }else{
            handelUpdate();
        }
    }

    const handelDelete = (e) => {

        const id = formData.educationId;
        
        const url = `http://localhost:8080/api/education/${formData.educationId}`

        const goAhead = window.confirm(`permenatly this education entry ${formData.universityName}?`);
        if(!goAhead){
            return;
        }
    
            const token = localStorage.getItem("jwtToken");
            const options = {
                method: "Delete",
                headers: {
                    Authorization: "Bearer " + token,
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            };
    
            fetch(url, options)
                .then((res) => {
                    if (res.status === 204) {
                        setOptions(prevOptions => {
                            // Filter out the option with the specified id
                            const updatedOptions = prevOptions.filter(option => option.value !== id);
                            return updatedOptions;
                        });
                        
                        setInformation(prevInfo => {
                            // Filter out the entry with the specified id
                            const newEntries = Object.fromEntries(
                                Object.entries(prevInfo).filter(([key]) => key !== id)
                            );
                            return newEntries;
                        });
                        
                        // Reset the form and set additional state if needed
                        setSelected( prevSelected => {
                            prevSelected.value=-1;
                            prevSelected.label = 'Select an option';
                        });
                    }else{
                        
                    }
    
                    return res.json();
                })
                .then((data) => {
                })
                .catch((e) => {

                });
    }

    return (
        <div className="w-auto p-2">
            <form className="bg-white rounded-md" onSubmit={handelSubmit}>
                <div className="mb-4">
                    <label
                        htmlFor="universityName"
                        className="block text-sm font-medium text-gray-700 mb-1"
                    >
                        Institution
                    </label>
                    <input
                        type="text"
                        id="universityName"
                        name="universityName"
                        value={formData.universityName || ''}
                        onChange={registerChange}
                        placeholder="Enter an Institution name"
                        className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>

                <div className="flex flex-wrap gap-4 mb-4">
                    <div className="flex-1">
                        <label
                            htmlFor="degree"
                            className="block text-sm font-medium text-gray-700 mb-1"
                        >
                            Degree
                        </label>
                        <input
                            type="text"
                            id="degree"
                            name="degree"
                            value={formData.degree ||''}
                            onChange={registerChange}
                            placeholder="B.S."
                            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                        />
                    </div>
                    <div className="flex-1">
                        <label
                            htmlFor="major"
                            className="block text-sm font-medium text-gray-700 mb-1"
                        >
                            Major
                        </label>
                        <input
                            type="text"
                            id="major"
                            name="major"
                            value={formData.major || ''}
                            onChange={registerChange}
                            placeholder="Computer Science"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                        />
                    </div>
                </div>

                <div className="mb-4">
                    <label
                        htmlFor="gpa"
                        className="block text-sm font-medium text-gray-700 mb-1"
                    >
                        gpa
                    </label>
                    <input
                        type="number"
                        step="0.01"
                        id="gpa"
                        name="gpa"
                        value={formData.gpa || ''}
                        onChange={registerChange}
                        placeholder="4.0"
                        className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                    />
                </div>

                <div className="flex flex-wrap gap-4 mb-4">
                    <div className="flex-1">
                        <label
                            htmlFor="startDate"
                            className="block text-sm font-medium text-gray-700 mb-1"
                        >
                            Start Date
                        </label>
                        <input
                            type="date"
                            id="startDate"
                            name="startDate"
                            value={formData.startDate || ''}
                            onChange={registerChange}
                            placeholder="01/01/2000"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                        />
                    </div>
                    <div className="flex-1">
                        <label
                            htmlFor="endDate"
                            className="block text-sm font-medium text-gray-700 mb-1"
                        >
                            End Date (if applicable)
                        </label>
                        <input
                            type="date"
                            id="endDate"
                            name="endDate"
                            value={formData.endDate || ''}
                            onChange={registerChange}
                            placeholder="01/01/2004"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500"
                        />
                    </div>
                </div>

                <div className="mb-4">
                    <label
                        htmlFor="description"
                        className="block text-sm font-medium text-gray-700 mb-1"
                    >
                        Description
                    </label>
                    <textarea
                        id="description"
                        name="description"
                        value={formData.description || ''}
                        onChange={registerChange}
                        placeholder="Describe your role and responsibilities"
                        rows="3"
                        className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 resize-none"
                    />
                </div>

                <div
                    className={
                        formData.educationId !== 0
                            ? "flex justify-between"
                            : "flex justify-end"
                    }
                >
                    {(formData.educationId>0 && (
                        <button
                            type="button"
                            onClick={handelDelete}
                            className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:bg-red-600"
                        >
                            Delete
                        </button>
                    ))}
                    <button type="submit" className={buttonClass}>
                        {formData.educationId === 0
                            ? "add new education"
                            : "confirm changes"}
                    </button>
                </div>
            </form>
        </div>
    );
}

export default EducationForm;
