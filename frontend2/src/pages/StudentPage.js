import { useEffect, useState } from "react";

function StudentPage({ setRole }) {

  const [task, setTask] = useState("");
  const [code, setCode] = useState("");
  const [result, setResult] = useState("");

  // 🔹 Task laden
  useEffect(() => {
    fetch("http://localhost:8080/api/tasks")
      .then(res => res.json())
      .then(data => {
        if (data.length > 0) {
          setTask(data[data.length - 1].description);
        }
      })
      .catch(err => console.error(err));
  }, []);

  // 🔹 Submit
  const submitCode = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/submit", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          studentCode: code,
        }),
      });

      const data = await res.json();
      setResult({
            score: data.score,
            feedback: data.feedback
            });

    } catch (error) {
      setResult("Fehler beim Senden ❌");
    }
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

        <h2 className="text-xl font-bold mb-4">📋 Aufgabe</h2>

        <div className="bg-gray-100 p-4 rounded mb-4">
          {task || "Keine Aufgabe vorhanden"}
        </div>

        <textarea
          className="w-full h-64 p-4 border rounded-xl"
          placeholder="Dein Code..."
          value={code}
          onChange={(e) => setCode(e.target.value)}
        />

        <button
          onClick={submitCode}
          className="mt-4 w-full bg-blue-600 text-white py-3 rounded-xl"
        >
          Submit Code
        </button>

        <div className="mt-6 bg-gray-900 text-green-400 p-4 rounded-xl shadow-inner">
  
            <h3 className="text-white font-bold mb-2">
                Score: {result?.score ?? "-"}
            </h3>

            <pre className="whitespace-pre-wrap text-sm leading-relaxed">
                {result?.feedback || "Noch kein Ergebnis"}
            </pre>

        </div>

      </div>
    </div>
  );
}

export default StudentPage;