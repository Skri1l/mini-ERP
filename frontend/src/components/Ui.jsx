export function Panel({ eyebrow, title, children }) {
  return (
    <section className="panel">
      <div className="panel-head">
        <span className="kicker">{eyebrow}</span>
        <h2>{title}</h2>
      </div>
      {children}
    </section>
  );
}

export function Field({ label, children }) {
  return (
    <label className="field">
      <span>{label}</span>
      {children}
    </label>
  );
}

export function Info({ label, value }) {
  return (
    <div className="info">
      <span>{label}</span>
      <strong>{value || "—"}</strong>
    </div>
  );
}

export function Status({ active }) {
  return (
    <span className={active ? "status active" : "status"}>
      {active ? "Active" : "Inactive"}
    </span>
  );
}

export function Empty({ text }) {
  return <div className="empty">{text}</div>;
}
