const API_BASE =
  import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api";

export async function apiRequest(path, options = {}) {
  const response = await fetch(`${API_BASE}${path}`, {
    headers: {
      "Content-Type": "application/json",
      ...(options.headers || {}),
    },
    ...options,
  });

  const text = await response.text();

  let data = null;
  if (text) {
    try {
      data = JSON.parse(text);
    } catch {
      data = text;
    }
  }

  if (!response.ok) {
    const message =
      data?.message ||
      (typeof data === "string" ? data : null) ||
      `HTTP ${response.status}`;

    throw new Error(message);
  }

  return data;
}

export { API_BASE };
