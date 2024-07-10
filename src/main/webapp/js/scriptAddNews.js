function validateForm() {
  var title = document.getElementById('title').value;
  var brief = document.getElementById('brief').value;
  var date = document.getElementById('date').value;
  var id = document.getElementById('id').value;
  var category = document.getElementById('category').value;
  
  if (!title || !brief || !date || !id || !category) {
    alert('Please fill in all fields.');
    return false;
  }
  
  return true;
}