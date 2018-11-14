import axios from "axios";
import * as React from "react"
import {Component, } from "react";
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
                    {this.state.taskList.tasks.map((item) => this.createTasks(item, this.props.id))}
                </ul>
            </div>
        );
    }

    private createTasks(item: any, id: string) {
        const newFn = () => this.deleteTask(item.id, id);
        return <li key={item.id}>{item.description} <button onClick={newFn}>❌</button></li>
    }

    private deleteTask = (id: string, tripId: string) => {
        axios
            .delete("/trips/" + tripId + "/tasks/" + id)
            .then( response => {
                const remainder = this.state.taskList.tasks.filter((item) => {
                    if(item.id !== id) {
                        return item;
                    }
                });
                this.setState({taskList: new TaskList(remainder)});
            })
            .catch((error) => {
                    alert(JSON.stringify(error));
                }
            );
    }
}