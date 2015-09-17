var mongoose = require('mongoose');
var bcrypt = require('bcrypt-nodejs');

var UserSchema = new mongoose.Schema({
	local            : {
        username     : String,
        password     : String,
        role         : Number		// 1 : student, 2 : admin
    },
    facebook         : {
        id           : String,
        token        : String,
        username     : String,
        name         : String
    },
    twitter          : {
        id           : String,
        token        : String,
        displayName  : String,
        username     : String
    },
    google           : {
        id           : String,
        token        : String,
        username     : String,
        name         : String
    }
});

// methods ======================
// generating a hash
UserSchema.methods.generateHash = function(password) {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(8), null);
};

// checking if password is valid
UserSchema.methods.validPassword = function(password) {
    return bcrypt.compareSync(password, this.local.password);
};

// checking if account is admin
// UserSchema.methods.isAdmin = function(id) {
//     this.model('User').findOne({_id: id, role : 2}, function (err, user) {
//         if (user) {
//             console.log("Admin: " + user);
//             return true;
//         }

//         return false;
//     });
// };

module.exports = mongoose.model('User', UserSchema)