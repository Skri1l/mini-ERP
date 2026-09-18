import { Building2, Stethoscope, Sparkles } from "lucide-react";
import { API_BASE } from "../api/client.js";

export default function Dashboard({ onNavigate }) {
  return (
    <>
      <section className="hero">
        <div>
          <span className="pill light">Mini ERP</span>
          <h2>Frontend for your dental clinic backend.</h2>
          <p>
            Это отдельное React-приложение. Оно ничего не знает о Spring Boot
            кроме адреса API.
          </p>

          <div className="hero-actions">
            <button className="button primary" onClick={() => onNavigate("clinics")}>
              Open clinics
            </button>
            <button className="button ghost" onClick={() => onNavigate("doctors")}>
              Manage doctors
            </button>
          </div>
        </div>

        <div className="architecture">
          <div className="arch-row">
            <span>Frontend</span>
            <strong>React + Vite</strong>
          </div>
          <div className="arch-line" />
          <div className="arch-row">
            <span>API URL</span>
            <strong>{API_BASE}</strong>
          </div>
          <div className="arch-line" />
          <div className="arch-row">
            <span>Backend</span>
            <strong>Your Spring Boot app</strong>
          </div>
        </div>
      </section>

      <section className="stats">
        <Stat icon={<Building2 />} title="Clinic" text="create & lookup" />
        <Stat icon={<Stethoscope />} title="Doctor" text="CRUD + deactivate" />
        <Stat icon={<Sparkles />} title="Procedure" text="CRUD + deactivate" />
      </section>
    </>
  );
}

function Stat({ icon, title, text }) {
  return (
    <div className="stat-card">
      <div className="icon-box">{icon}</div>
      <div>
        <strong>{title}</strong>
        <span>{text}</span>
      </div>
    </div>
  );
}
