import { useState } from "react";
import LoginPage from "./pages/LoginPage";
import StudentPage from "./pages/StudentPage";
import ProfessorPage from "./pages/ProfessorPage";

function App() {
  const [role, setRole] = useState(null);

  if (!role) {
    return <LoginPage setRole={setRole} />;
  }

  if (role === "student") {
    return <StudentPage setRole={setRole} />;
  }

  if (role === "professor") {
    return <ProfessorPage setRole={setRole} />;
  }
}

export default App;