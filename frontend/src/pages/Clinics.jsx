import { useState } from "react";
import { Plus, Search } from "lucide-react";
import { apiRequest } from "../api/client.js";
import { Panel, Field, Info, Empty } from "../components/Ui.jsx";

export default function Clinics({ notify }) {
  const [clinicId, setClinicId] = useState(1);
  const [clinic, setClinic] = useState(null);
  const [form, setForm] = useState({
    clinicName: "",
    phoneNumber: "",
    address: "",
  });

  async function createClinic(e) {
    e.preventDefault();

    try {
      const id = await apiRequest("/clinics", {
        method: "POST",
        body: JSON.stringify(form),
      });

      notify(`Clinic created. ID: ${id}`);
      setClinicId(id);
      setForm({ clinicName: "", phoneNumber: "", address: "" });
    } catch (error) {
      notify(error.message);
    }
  }

  async function findClinic(e) {
    e.preventDefault();

    try {
      const result = await apiRequest(`/clinics/${clinicId}`);
      setClinic(result);
      notify("Clinic loaded");
    } catch (error) {
      setClinic(null);
      notify(error.message);
    }
  }

  return (
    <div className="grid-two">
      <Panel eyebrow="POST /api/clinics" title="Create clinic">
        <form className="form" onSubmit={createClinic}>
          <Field label="Clinic name">
            <input
              value={form.clinicName}
              onChange={(e) => setForm({ ...form, clinicName: e.target.value })}
              placeholder="Smile Dental"
              required
            />
          </Field>

          <Field label="Phone">
            <input
              value={form.phoneNumber}
              onChange={(e) => setForm({ ...form, phoneNumber: e.target.value })}
              placeholder="+37060000000"
              required
            />
          </Field>

          <Field label="Address">
            <input
              value={form.address}
              onChange={(e) => setForm({ ...form, address: e.target.value })}
              placeholder="Vilnius, Gedimino pr. 1"
              required
            />
          </Field>

          <button className="button primary">
            <Plus size={17} />
            Create
          </button>
        </form>
      </Panel>

      <Panel eyebrow="GET /api/clinics/{id}" title="Find clinic">
        <form className="search-row" onSubmit={findClinic}>
          <input
            type="number"
            min="1"
            value={clinicId}
            onChange={(e) => setClinicId(Number(e.target.value))}
          />

          <button className="button secondary">
            <Search size={17} />
            Find
          </button>
        </form>

        {clinic ? (
          <div className="entity feature">
            <span className="tiny">CLINIC #{clinic.id}</span>
            <h3>{clinic.clinicName}</h3>

            <div className="info-grid">
              <Info label="Phone" value={clinic.phoneNumber} />
              <Info label="Address" value={clinic.address} />
            </div>
          </div>
        ) : (
          <Empty text="Enter a clinic ID to load clinic data." />
        )}
      </Panel>
    </div>
  );
}
