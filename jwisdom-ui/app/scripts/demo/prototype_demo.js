var Animal = function(name) {
	this.name = name;
};

Animal.prototype = {
	shout: function() {
		console.log(this.name);
	}
};

var Person = function(name, age) {
	Animal.call(this, name);
	this.age = age;
};

Person.prototype = Object.create(Animal.prototype, {
	shout: {
		value: function() {
			// Animal.prototype.shout.apply(this, arguments);
			console.log(this.name + " is " + this.age + " old");
		}
	}
});

//Person.prototype.constructor = Person;

var jack = new Animal('jack');
jack.shout();

var tom = new Person('tom', 20);
tom.shout();