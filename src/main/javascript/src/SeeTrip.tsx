import axios from 'axios';
import * as React from "react"
import {Component} from "react";

interface Props {
    id: string
}

interface State {
    found: boolean
    trip: any
}

export default class SeeTrip extends Component<Props, State> {

    constructor(props: Props) {
        super(props);
        this.state = {
            found: false,
            trip: {}
        }
    }

    public componentDidMount() {
        axios.get("/trips/" + this.props.id)
            .then((response) => {
                console.log(JSON.stringify(response));
                this.setState({trip: response.data});
            }).catch((error) => {
            alert(JSON.stringify(error))
        });
    }

    public render() {
        return (
            <div>Created a trip with ID: {JSON.stringify(this.state.trip)}</div>
        );
    }
}
