function LoginPage({ setRole }) {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">

      <div className="bg-white p-10 rounded-2xl shadow-xl text-center w-80">

        <h1 className="text-2xl font-bold mb-6">
          🎓 Code Platform
        </h1>

        <button
          onClick={() => setRole("student")}
          className="w-full mb-4 bg-blue-600 text-white py-3 rounded-xl hover:bg-blue-700"
        >
          👨‍🎓 Student
        </button>

        <button
          onClick={() => setRole("professor")}
          className="w-full bg-gray-800 text-white py-3 rounded-xl hover:bg-gray-900"
        >
          👨‍🏫 Professor
        </button>

      </div>
    </div>
  );
}

export default LoginPage;