import {
  LayoutDashboard,
  Building2,
  Stethoscope,
  Sparkles,
} from "lucide-react";

const items = [
  ["dashboard", LayoutDashboard, "Overview"],
  ["clinics", Building2, "Clinics"],
  ["doctors", Stethoscope, "Doctors"],
  ["procedures", Sparkles, "Procedures"],
];

export default function Sidebar({ page, onChange }) {
  return (
    <aside className="sidebar">
      <div className="logo">
        <div className="logo-mark">M</div>
        <div>
          <strong>Mini ERP</strong>
          <small>Dental clinic</small>
        </div>
      </div>

      <nav>
        {items.map(([key, Icon, label]) => (
          <button
            key={key}
            className={page === key ? "nav-button active" : "nav-button"}
            onClick={() => onChange(key)}
          >
            <Icon size={18} />
            {label}
          </button>
        ))}
      </nav>

      <div className="sidebar-bottom">
        Frontend service
      </div>
    </aside>
  );
}
