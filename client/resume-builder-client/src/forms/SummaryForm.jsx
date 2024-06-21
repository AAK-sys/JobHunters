function SummaryForm({ formData, setFormData, handleChange, setOptions, setSelected, information, setInformation }) {

    const defaultSummary = {
        summaryId: 0,
        displayName: "",
        description: "",
        userId: formData.userId
    };

    const URL =
        formData.summaryId === 0
            ? "http://localhost:8080/api/summary"
            : `http://localhost:8080/api/summary/${formData.summaryId}`;

    const registerChange = (event) => {
        handleChange(event);
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
                console.log(res.status);
                if (res.status === 204) {
                    return null;
                } else if (res.status === 201 || res.status === 400) {
                    return res.json();
                } else {
                    return Promise.reject(
                        `Unexpected status code: ${res.status}`
                    );
                }
            })
            .then((data) => {
                if (data && !data.summaryId) {
                    alert(data);
                }else if(data){ //add
                    setOptions(prevOptions => {
                        const newOption = { value: data.summaryId, label: data.displayName };
                        const lastOption = prevOptions[prevOptions.length - 1];
                        return [...prevOptions.slice(0, -1), newOption, lastOption];
                    });
                    setInformation(prevInfo => {
                        const entries = Object.entries(prevInfo);
                        const lastEntry = entries.pop();
                        const newEntries = [...entries, [data.summaryId, data], lastEntry];
                        return Object.fromEntries(newEntries);
                    })
                    const newInformation = information;
                    newInformation[0] = defaultSummary;
                    setFormData(defaultSummary);
                    setInformation(newInformation);}

            })
            .catch((e) => {

            });
    };

    const deleteExp = (e) => {
        const id = formData.summaryId;
        const goAhead = window.confirm(`permenatly delete ${formData.displayName}?`);
        if(!goAhead){
            return;
        }

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
                        value={formData.displayName || ''}
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
                        value={formData.description || ''}
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
                            className="px-4 py-2 rounded-md deleteBtn"
                        >
                            Delete
                        </button>
                    )) ||
                        ""}
                    <button
                        type="submit"
                        name="wildcard"
                        onClick={addOrUpdate}
                        className="px-4 py-2 rounded-md submitBtn"
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
