var gulp = require('gulp');

var plugins = require('gulp-load-plugins')();
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');
var reactify = require('reactify');

var path = {
	ENTRY_POINT : 'static/modules/app.js',
	MINIFIED_OUT: 'bundle.js'
};

gulp.task('default', function(){
	console.log('hello world');
});

gulp.task('build', function() {
	// gulp.src('public/js/main.js')
	browserify({
        entries: [path.ENTRY_POINT],
        transform: [reactify]
    })
    	.bundle()
    	.pipe(source(path.MINIFIED_OUT))
    	.pipe(buffer())
		//.pipe(plugins.uglify().on('error', plugins.util.log)) // compress
		// .pipe(plugins.rename('main.min.js')) // rename 
		.pipe(gulp.dest('build')); // genreate example.min.js
});

gulp.watch('static/modules/**/*.js', ['build']);