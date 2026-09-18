import { useState } from "react";
import { Pencil, Plus, Power, RefreshCw } from "lucide-react";
import { apiRequest } from "../api/client.js";
import { Empty, Field, Info, Panel, Status } from "../components/Ui.jsx";

const emptyDoctor = {
  firstName: "",
  lastName: "",
  specialization: "",
  phoneNumber: "",
  email: "",
  photoUrl: "",
};

export default function Doctors({ notify }) {
  const [clinicId, setClinicId] = useState(1);
  const [doctors, setDoctors] = useState([]);
  const [form, setForm] = useState(emptyDoctor);
  const [editingId, setEditingId] = useState(null);

  async function loadDoctors() {
    try {
      const result = await apiRequest(`/clinics/${clinicId}/doctors`);
      setDoctors(result || []);
      notify("Doctors loaded");
    } catch (error) {
      setDoctors([]);
      notify(error.message);
    }
  }

  async function saveDoctor(e) {
    e.preventDefault();

    const body = {
      ...form,
      photoUrl: form.photoUrl || null,
    };

    try {
      if (editingId) {
        await apiRequest(`/doctors/${editingId}`, {
          method: "PUT",
          body: JSON.stringify(body),
        });
        notify(`Doctor #${editingId} updated`);
      } else {
        const id = await apiRequest(`/clinics/${clinicId}/doctors`, {
          method: "POST",
          body: JSON.stringify(body),
        });
        notify(`Doctor created. ID: ${id}`);
      }

      setForm(emptyDoctor);
      setEditingId(null);
      await loadDoctors();
    } catch (error) {
      notify(error.message);
    }
  }

  function editDoctor(doctor) {
    setEditingId(doctor.id);
    setForm({
      firstName: doctor.firstName || "",
      lastName: doctor.lastName || "",
      specialization: doctor.specialization || "",
      phoneNumber: doctor.phoneNumber || "",
      email: doctor.email || "",
      photoUrl: doctor.photoUrl || "",
    });
  }

  async function deactivateDoctor(id) {
    try {
      await apiRequest(`/doctors/${id}/deactivate`, {
        method: "PATCH",
      });
      notify(`Doctor #${id} deactivated`);
      await loadDoctors();
    } catch (error) {
      notify(error.message);
    }
  }

  return (
    <>
      <div className="toolbar panel">
        <div>
          <span className="kicker">CLINIC CONTEXT</span>
          <h3>Doctors by clinic</h3>
        </div>

        <div className="search-row no-margin">
          <input
            type="number"
            min="1"
            value={clinicId}
            onChange={(e) => setClinicId(Number(e.target.value))}
          />

          <button className="button secondary" onClick={loadDoctors}>
            <RefreshCw size={17} />
            Load
          </button>
        </div>
      </div>

      <div className="grid-form-list">
        <Panel
          eyebrow={editingId ? `PUT /api/doctors/${editingId}` : "POST /api/clinics/{id}/doctors"}
          title={editingId ? "Edit doctor" : "Add doctor"}
        >
          <form className="form" onSubmit={saveDoctor}>
            <div className="row">
              <Field label="First name">
                <input
                  value={form.firstName}
                  onChange={(e) => setForm({ ...form, firstName: e.target.value })}
                  required
                />
              </Field>

              <Field label="Last name">
                <input
                  value={form.lastName}
                  onChange={(e) => setForm({ ...form, lastName: e.target.value })}
                  required
                />
              </Field>
            </div>

            <Field label="Specialization">
              <input
                value={form.specialization}
                onChange={(e) => setForm({ ...form, specialization: e.target.value })}
                required
              />
            </Field>

            <Field label="Phone">
              <input
                value={form.phoneNumber}
                onChange={(e) => setForm({ ...form, phoneNumber: e.target.value })}
                required
              />
            </Field>

            <Field label="Email">
              <input
                type="email"
                value={form.email}
                onChange={(e) => setForm({ ...form, email: e.target.value })}
                required
              />
            </Field>

            <Field label="Photo URL">
              <input
                value={form.photoUrl}
                onChange={(e) => setForm({ ...form, photoUrl: e.target.value })}
                placeholder="https://..."
              />
            </Field>

            <div className="action-row">
              <button className="button primary">
                {editingId ? <Pencil size={17} /> : <Plus size={17} />}
                {editingId ? "Save changes" : "Add doctor"}
              </button>

              {editingId && (
                <button
                  type="button"
                  className="button secondary"
                  onClick={() => {
                    setEditingId(null);
                    setForm(emptyDoctor);
                  }}
                >
                  Cancel
                </button>
              )}
            </div>
          </form>
        </Panel>

        <Panel eyebrow="GET /api/clinics/{id}/doctors" title="Doctors">
          <div className="list">
            {doctors.length ? (
              doctors.map((doctor) => (
                <article className="entity" key={doctor.id}>
                  <div className="entity-head">
                    <div className="person">
                      <div className="avatar">
                        {doctor.photoUrl ? (
                          <img src={doctor.photoUrl} alt="" />
                        ) : (
                          `${doctor.firstName?.[0] || ""}${doctor.lastName?.[0] || ""}`
                        )}
                      </div>

                      <div>
                        <h3>{doctor.firstName} {doctor.lastName}</h3>
                        <p>{doctor.specialization}</p>
                      </div>
                    </div>

                    <Status active={doctor.active} />
                  </div>

                  <div className="info-grid">
                    <Info label="Email" value={doctor.email} />
                    <Info label="Phone" value={doctor.phoneNumber} />
                  </div>

                  <div className="action-row">
                    <button className="mini-button" onClick={() => editDoctor(doctor)}>
                      <Pencil size={15} />
                      Edit
                    </button>

                    {doctor.active && (
                      <button
                        className="mini-button danger"
                        onClick={() => deactivateDoctor(doctor.id)}
                      >
                        <Power size={15} />
                        Deactivate
                      </button>
                    )}
                  </div>
                </article>
              ))
            ) : (
              <Empty text="No doctors loaded yet." />
            )}
          </div>
        </Panel>
      </div>
    </>
  );
}
