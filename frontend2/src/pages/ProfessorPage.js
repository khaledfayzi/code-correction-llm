import { useState } from "react";

function ProfessorPage({ setRole }) {

  const [task, setTask] = useState("");

  const saveTask = async () => {
    await fetch("http://localhost:8080/api/tasks", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        description: task,
        expectedOutput: "",
      }),
    });

    alert("Task gespeichert ✅");
    setTask("");
  };

  return (
    <div className="max-w-4xl mx-auto mt-10">

      {/* 🔥 BACK BUTTON */}
      <button
        onClick={() => setRole(null)}
        className="mb-4 bg-gray-500 text-white px-4 py-2 rounded"
      >
        ⬅ Zurück
      </button>

      <div className="bg-white p-6 rounded-2xl shadow-lg">

        <h2 className="text-xl font-bold mb-4">
          👨‍🏫 Neue Aufgabe
        </h2>

        <textarea
          className="w-full h-40 p-4 border rounded-xl"
          placeholder="Aufgabe..."
          value={task}
          onChange={(e) => setTask(e.target.value)}
        />

        <button
          onClick={saveTask}
          className="mt-4 w-full bg-green-600 text-white py-3 rounded-xl"
        >
          Speichern
        </button>

      </div>
    </div>
  );
}

export default ProfessorPage;