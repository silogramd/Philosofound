const path = require('path');
const webpack = require('webpack');

module.exports = {
    entry: './src/main/js/index.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        filename:'bundle.js',
        path: path.resolve(__dirname, 'dist')
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            }
        ]
    },
    plugins: [
        new webpack.ProvidePlugin({
            "React": "react"
        })
    ]
};
