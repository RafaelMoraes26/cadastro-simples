function toLocalDateTime(date) {
    function pad(number) {
        return number < 10 ? '0' + number : number;
    }

    return date.getFullYear() +
        '-' + pad(date.getMonth() + 1) +
        '-' + pad(date.getDate()) +
        'T' + pad(date.getHours()) +
        ':' + pad(date.getMinutes()) +
        ':' + pad(date.getSeconds());
}

let yesterday = new Date();
yesterday = yesterday.setDate(yesterday.getDate() - 1);

db = db.getSiblingDB('cadastroSimples');

db.userInfo.insertMany([
    { "_id": ObjectId(), "fullName": "John Doe", "username": "johndoe", "email": "john.doe@example.com", "createdDate": toLocalDateTime(yesterday), "updatedDate": toLocalDateTime(yesterday) },
    { "_id": ObjectId(), "fullName": "Jane Doe", "username": "janedoe", "email": "jane.doe@example.com", "createdDate": toLocalDateTime(yesterday), "updatedDate": toLocalDateTime(yesterday) },
    { "_id": ObjectId(), "fullName": "Sam Smith", "username": "samsmith", "email": "sam.smith@example.com", "createdDate": toLocalDateTime(yesterday), "updatedDate": toLocalDateTime(yesterday) }
]);

db.userPost.insertMany([
    { "_id": ObjectId(), "_class": "br.com.cadastro.simples.repository.document.UserPostDocument", "title": "First Post", "content": "Content of the first post", "username": "johndoe", "createdDate": toLocalDateTime(yesterday), "updatedDate": toLocalDateTime(yesterday) },
    { "_id": ObjectId(), "_class": "br.com.cadastro.simples.repository.document.UserPostDocument", "title": "Second Post", "content": "Content of the second post", "username": "janedoe", "createdDate": toLocalDateTime(yesterday), "updatedDate": toLocalDateTime(yesterday) },
    { "_id": ObjectId(), "_class": "br.com.cadastro.simples.repository.document.UserPostDocument", "title": "Third Post", "content": "Content of the third post", "username": "samsmith", "createdDate": toLocalDateTime(yesterday), "updatedDate": toLocalDateTime(yesterday) }
]);
