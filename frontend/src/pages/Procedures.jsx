import { useState } from "react";
import { Pencil, Plus, Power, RefreshCw } from "lucide-react";
import { apiRequest } from "../api/client.js";
import { Empty, Field, Info, Panel, Status } from "../components/Ui.jsx";

const emptyProcedure = {
  procedureName: "",
  description: "",
  price: "",
  durationMinutes: "",
};

export default function Procedures({ notify }) {
  const [clinicId, setClinicId] = useState(1);
  const [procedures, setProcedures] = useState([]);
  const [form, setForm] = useState(emptyProcedure);
  const [editingId, setEditingId] = useState(null);

  async function loadProcedures() {
    try {
      const result = await apiRequest(`/clinics/${clinicId}/procedures`);
      setProcedures(result || []);
      notify("Procedures loaded");
    } catch (error) {
      setProcedures([]);
      notify(error.message);
    }
  }

  async function saveProcedure(e) {
    e.preventDefault();

    const body = {
      ...form,
      price: Number(form.price),
      durationMinutes: Number(form.durationMinutes),
    };

    try {
      if (editingId) {
        await apiRequest(`/procedures/${editingId}`, {
          method: "PUT",
          body: JSON.stringify(body),
        });
        notify(`Procedure #${editingId} updated`);
      } else {
        await apiRequest(`/clinics/${clinicId}/procedures`, {
          method: "POST",
          body: JSON.stringify(body),
        });
        notify("Procedure created");
      }

      setForm(emptyProcedure);
      setEditingId(null);
      await loadProcedures();
    } catch (error) {
      notify(error.message);
    }
  }

  function editProcedure(procedure) {
    setEditingId(procedure.id);
    setForm({
      procedureName: procedure.procedureName || "",
      description: procedure.description || "",
      price: procedure.price ?? "",
      durationMinutes: procedure.durationMinutes ?? "",
    });
  }

  async function deactivateProcedure(id) {
    try {
      await apiRequest(`/procedures/${id}/deactivate`, {
        method: "PATCH",
      });
      notify(`Procedure #${id} deactivated`);
      await loadProcedures();
    } catch (error) {
      notify(error.message);
    }
  }

  return (
    <>
      <div className="toolbar panel">
        <div>
          <span className="kicker">CLINIC CONTEXT</span>
          <h3>Procedures by clinic</h3>
        </div>

        <div className="search-row no-margin">
          <input
            type="number"
            min="1"
            value={clinicId}
            onChange={(e) => setClinicId(Number(e.target.value))}
          />

          <button className="button secondary" onClick={loadProcedures}>
            <RefreshCw size={17} />
            Load
          </button>
        </div>
      </div>

      <div className="grid-form-list">
        <Panel
          eyebrow={editingId ? `PUT /api/procedures/${editingId}` : "POST /api/clinics/{id}/procedures"}
          title={editingId ? "Edit procedure" : "Add procedure"}
        >
          <form className="form" onSubmit={saveProcedure}>
            <Field label="Name">
              <input
                value={form.procedureName}
                onChange={(e) => setForm({ ...form, procedureName: e.target.value })}
                required
              />
            </Field>

            <Field label="Description">
              <textarea
                rows="4"
                value={form.description}
                onChange={(e) => setForm({ ...form, description: e.target.value })}
                required
              />
            </Field>

            <div className="row">
              <Field label="Price">
                <input
                  type="number"
                  step="0.01"
                  min="0.01"
                  value={form.price}
                  onChange={(e) => setForm({ ...form, price: e.target.value })}
                  required
                />
              </Field>

              <Field label="Minutes">
                <input
                  type="number"
                  min="1"
                  value={form.durationMinutes}
                  onChange={(e) => setForm({ ...form, durationMinutes: e.target.value })}
                  required
                />
              </Field>
            </div>

            <div className="action-row">
              <button className="button primary">
                {editingId ? <Pencil size={17} /> : <Plus size={17} />}
                {editingId ? "Save changes" : "Add procedure"}
              </button>

              {editingId && (
                <button
                  type="button"
                  className="button secondary"
                  onClick={() => {
                    setEditingId(null);
                    setForm(emptyProcedure);
                  }}
                >
                  Cancel
                </button>
              )}
            </div>
          </form>
        </Panel>

        <Panel eyebrow="GET /api/clinics/{id}/procedures" title="Procedures">
          <div className="list">
            {procedures.length ? (
              procedures.map((procedure) => (
                <article className="entity" key={procedure.id}>
                  <div className="entity-head">
                    <div>
                      <h3>{procedure.procedureName}</h3>
                      <p>{procedure.description}</p>
                    </div>

                    <Status active={procedure.active} />
                  </div>

                  <div className="info-grid">
                    <Info label="Price" value={procedure.price} />
                    <Info label="Duration" value={`${procedure.durationMinutes} min`} />
                  </div>

                  <div className="action-row">
                    <button className="mini-button" onClick={() => editProcedure(procedure)}>
                      <Pencil size={15} />
                      Edit
                    </button>

                    {procedure.active && (
                      <button
                        className="mini-button danger"
                        onClick={() => deactivateProcedure(procedure.id)}
                      >
                        <Power size={15} />
                        Deactivate
                      </button>
                    )}
                  </div>
                </article>
              ))
            ) : (
              <Empty text="No procedures loaded yet." />
            )}
          </div>
        </Panel>
      </div>
    </>
  );
}
