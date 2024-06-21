import { useState } from "react";

function UserInfoForm({ formData, setFormData }) {
    const [prepareForChange, setPrepareForChange] = useState(false);

    const EXP_URL = `http://localhost:8080/api/userinfo/${formData.userInfoId}`;

    const buttonClass = !prepareForChange
        ? "transition ease-in duration-1000 text-black bg-gray-500 px-4 py-2 rounded-md"
        : "transition ease-in duration-1000 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600";

    const handelAdd =  () => {
        
        const url = "http://localhost:8080/api/userinfo";

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
                        `your data has been ${
                            res.status === 201 ? "Added" : "updated"
                        }`
                    );
                }

                return res.json();
            })
            .then((data) => {
                if(data && !data.userInfoId){
                    alert(data);
                }
            })
            .catch((e) => {
            });
    };

    const handelUpdate = () => {

        const token = localStorage.getItem("jwtToken");
        const options = {
            method: "PUT",
            headers: {
                Authorization: "Bearer " + token,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        };

        fetch(`${EXP_URL}`, options)
            .then((res) => {
                if (res.status === 201 || res.status === 204) {
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

    const handleChange = (e) => {
        setPrepareForChange(true);
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handelSubmit = (event) => {
        event.preventDefault();

        if(formData.userInfoId === 0){// add
            handelAdd();
        }else{
            handelUpdate();
        }
    };

    return (
        <div className="m-3 shadow rounded-lg p-4">
            <form
                className="p-3 pt-4 bg-white rounded-md"
                onSubmit={handelSubmit}
            >
                <h2 className="text-xl font-semibold text-gray-900 mb-4">
                    Personal Information
                </h2>
                <div className="flex flex-col gap-3">
                    <div className="flex flex-col gap-1">
                        <label className="text-base font-medium text-gray-700">
                            Name
                        </label>
                        <input
                            type="text"
                            name="fullName"
                            value={formData.fullName || ""}
                            onChange={handleChange}
                            className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                        />
                    </div>
                    <div className="grid grid-cols-2 gap-3">
                        <div className="flex flex-col gap-1">
                            <label className="text-base font-medium text-gray-700">
                                Email
                            </label>
                            <input
                                type="text"
                                name="email"
                                value={formData.email || ""}
                                onChange={handleChange}
                                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                            />
                        </div>
                        <div className="flex flex-col gap-1">
                            <label className="text-base font-medium text-gray-700">
                                Phone
                            </label>
                            <input
                                type="text"
                                name="phone"
                                value={formData.phone || ""}
                                onChange={handleChange}
                                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                            />
                        </div>
                    </div>
                    <div className="grid grid-cols-2 gap-3 mb-3">
                        <div className="flex flex-col gap-1">
                            <label className="text-base font-medium text-gray-700">
                                Website
                            </label>
                            <input
                                type="text"
                                placeholder="linkedin.com/in/username"
                                name="website"
                                value={formData.website || ""}
                                onChange={handleChange}
                                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                            />
                        </div>
                        <div className="flex flex-col gap-1">
                            <label className="text-base font-medium text-gray-700">
                                Location
                            </label>
                            <input
                                type="text"
                                name="location"
                                value={formData.location || ""}
                                onChange={handleChange}
                                className="px-3 py-2 border border-gray-300 rounded-md shadow-sm outline-none text-gray-900"
                            />
                        </div>
                    </div>
                </div>
                <div className="flex justify-end">
                    <button
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

export default UserInfoForm;
