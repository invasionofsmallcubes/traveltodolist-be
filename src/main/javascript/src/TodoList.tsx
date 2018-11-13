import axios from "axios";
import * as React from "react"
import {Component} from "react";
import TaskList from "./TaskList";

interface Props {
    id: string
}

interface State {
    taskList: TaskList
}

export default class TodoList extends Component<Props, State> {

    constructor(props: Props) {
        super(props);
        console.log(JSON.stringify(props));
        this.state = {
            taskList: new TaskList([])
        }
    }

    public componentDidMount() {
        axios.get("/trips/" + this.props.id + "/tasks")
            .then((response) => {
                console.log(JSON.stringify(response));
                this.setState({
                    taskList: new TaskList(response.data)
                });
            }).catch((error) => {
            console.log("TodoList! " + JSON.stringify(error));
            alert(JSON.stringify(error))
        });
    }

    public render() {
        return (
            <div>
                <ul>
                    {this.state.taskList.tasks.map((value) => this.createTasks(value))}
                </ul>
            </div>
        );
    }

    private createTasks(item: any) {
        return <li key={item.id}>{item.description}</li>
    }
}