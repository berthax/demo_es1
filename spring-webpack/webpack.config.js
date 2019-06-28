const path = require('path');

module.exports = {
  entry: './src/main/resources/app/js/myapp.js',
  devtool: 'eval-source-map',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, './src/main/resources/static/js')
  }
};