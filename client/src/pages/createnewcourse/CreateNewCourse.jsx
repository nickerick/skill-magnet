import Header from "../../components/Header";
import BasicCourseInformation from "../../components/courseinformation/BasicCourseInformation";
export default function CreateNewCourse() {
    return(
        <div className="create-course">
            <div ClassName="page-title"><Header headerName="Course Information" /></div>
            <BasicCourseInformation />
        </div>
    ); 
}