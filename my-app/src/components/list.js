export function getList() {
  return fetch('http://localhost:8080/services/list')
    .then(data => data.json())
}