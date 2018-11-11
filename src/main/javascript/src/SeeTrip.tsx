import * as React from "react"
import {Component} from "react";

interface Props {
    id: string
}

export default class SeeTrip extends Component<Props> {

    constructor(props: Props) {
        super(props);
    }

    public render() {
        return (
            <div>Created a trip with ID: {this.props.id}</div>
        );
    }
}
