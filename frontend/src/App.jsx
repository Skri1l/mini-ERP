import { useState } from "react";
import Sidebar from "./components/Sidebar.jsx";
import Dashboard from "./pages/Dashboard.jsx";
import Clinics from "./pages/Clinics.jsx";
import Doctors from "./pages/Doctors.jsx";
import Procedures from "./pages/Procedures.jsx";

const titles = {
  dashboard: "Overview",
  clinics: "Clinics",
  doctors: "Doctors",
  procedures: "Procedures",
};

export default function App() {
  const [page, setPage] = useState("dashboard");
  const [message, setMessage] = useState("");

  function notify(text) {
    setMessage(text);
    window.clearTimeout(window.__toastTimer);
    window.__toastTimer = window.setTimeout(() => setMessage(""), 3000);
  }

  return (
    <div className="shell">
      <Sidebar page={page} onChange={setPage} />

      <main className="main">
        <header className="topbar">
          <div>
            <span className="kicker">DENTAL MANAGEMENT</span>
            <h1>{titles[page]}</h1>
          </div>

          <div className="api-badge">
            separate frontend service
          </div>
        </header>

        {page === "dashboard" && <Dashboard onNavigate={setPage} />}
        {page === "clinics" && <Clinics notify={notify} />}
        {page === "doctors" && <Doctors notify={notify} />}
        {page === "procedures" && <Procedures notify={notify} />}
      </main>

      {message && <div className="toast">{message}</div>}
    </div>
  );
}
