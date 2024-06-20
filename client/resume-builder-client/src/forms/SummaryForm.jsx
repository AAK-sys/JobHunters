import { useState } from "react";

function SummaryForm({ formData, handleChange }) {
    const [prepareForChange, setPrepareForChange] = useState(false);

    const URL =
        formData.summaryId === 0
            ? "http://localhost:8080/api/summary"
            : `http://localhost:8080/api/summary/${formData.summaryId}`;

    const buttonClass = !prepareForChange
        ? "transition ease-in duration-1000 text-black bg-gray-500 px-4 py-2 rounded-md"
        : "transition ease-in duration-1000 bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:bg-blue-600";

    const registerChange = (event) => {
        handleChange(event);
        setPrepareForChange(true);
    };

    const addOrUpdate = (e) => {
        e.preventDefault();
        const token = localStorage.getItem("jwtToken");
        const options = {
            method: formData.summaryId === 0 ? "POST" : "PUT",
            headers: {
                Authorization: "Bearer " + token,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData),
        };

        fetch(`${URL}`, options)
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
                    alert(data, "hello from data");
                    console.log(data);
                }
            })
            .catch((e) => {
                //alert(e);
            });
    };

    const deleteExp = (e) => {
        e.preventDefault();
        const token = localStorage.getItem("jwtToken");
        const options = {
            method: "Delete",
            headers: {
                Authorization: "Bearer " + token,
            },
        };

        fetch(`${URL}`, options)
            .then((res) => {
                if (res.status === 204) {
                    alert(`your data has been deleted`);
                }

                return res.json();
            })
            .then((data) => {
                if (data) {
                    alert(data);
                }
            })
            .catch((e) => {
                //alert(e); will revist with time.
            });
    };

    return (
        <div className="w-auto p-2">
            <form className="bg-white rounded-md">
                <div className="mb-4">
                    <label
                        htmlFor="displayName"
                        className="block text-sm font-medium text-gray-700 mb-1"
                    >
                        Display Name
                    </label>
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
                    <label
                        htmlFor="description"
                        className="block text-sm font-medium text-gray-700 mb-1"
                    >
                        Description
                    </label>
                    <textarea
                        id="description"
                        name="description"
                        value={formData.description}
                        onChange={registerChange}
                        placeholder="who are you?"
                        rows="3"
                        className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 resize-none"
                    />
                </div>

                <div
                    className={
                        formData.summaryId !== 0
                            ? "flex justify-between"
                            : "flex justify-end"
                    }
                >
                    {(formData.summaryId && (
                        <button
                            type="button"
                            name="delete"
                            onClick={deleteExp}
                            className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:bg-red-600"
                        >
                            Delete
                        </button>
                    )) ||
                        ""}
                    <button
                        type="submit"
                        name="wildcard"
                        onClick={addOrUpdate}
                        className={buttonClass}
                    >
                        {formData.summaryId === 0
                            ? "add new summary"
                            : "confirm changes"}
                    </button>
                </div>
            </form>
        </div>
    );
}

export default SummaryForm;
