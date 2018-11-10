import * as React from 'react';

import {Component} from "react";
import './App.css';
import LoadingIndicator from './LoadingIndicator';
import logo from './logo.svg';


interface State {
    isLoading: boolean
}


export default class App extends Component<any, State> {
    private delayTimer: any;

    constructor(props: any) {
        super(props);
        this.state = { isLoading : false }
    }


    public componentDidMount() {
        this.delayTimer = setTimeout(
            () => this.setState({isLoading: false}),
            2000
        );
    }

    public componentWillUnmount() {
        clearTimeout(this.delayTimer);
    }


    public render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                    <h1 className="App-title">Welcome to React</h1>
                </header>
                <pre>isLoading: {String(this.state.isLoading)}</pre>
                <LoadingIndicator isLoading={this.state.isLoading}>
                    <div>ahoy!</div>
                </LoadingIndicator>
            </div>
        );
    }
}