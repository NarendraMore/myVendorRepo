import { Component, OnInit } from "@angular/core";
import { FormArray, FormControl, FormGroup, Validators } from "@angular/forms";
import { VendorMngServiceService } from "src/app/vendor-mng-service.service";
import { saveAs } from 'file-saver';
import {
  BusinessUser,
  Client,
  Industry,
  Manager,
  newProject,
  Partner,
  Project,
  Rfp,
} from "./model/project";
import { ConfirmationService, MessageService } from "primeng/api";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: "app-project-mng",
  templateUrl: "./project-mng.component.html",
  styleUrls: ["./project-mng.component.css"],
  providers: [ConfirmationService, MessageService],
})
export class ProjectMngComponent implements OnInit {
  newProjectDialog: boolean = false;

  clientForm1!: FormGroup;
  clientForm2!: FormGroup;
  clientForm3!: FormGroup;
  projectForm!: FormGroup;
  rfpForm!: FormGroup;

  clientInfo: newProject[] = [];
  projectInfo: Project[] = [];
  rfpInfo: Rfp[] = [];

  results: any[] = [];

  selectedIndustry!: string;
  selectedPartner!: string;
  selectedManager!: string;
  selectedBusinessUserName: string[] = [];

  clientData: any;
  projectData: any;
  rfpData: any;

  businessUsers: BusinessUser[] = [];
  managers: Manager[] = [];
  partners: Partner[] = [];

  industryNames: Industry[] = [];

  index: number = 0;
  newindex: number = 0;

  //edit functionalty
  newprojectData!: newProject;
  editProjectDialog: boolean = false;

  selectedFiles?: FileList; 
  currentFile?: File;

  selectedId: string[] = [];

  openNext() {
    this.messageService.add({
      severity: "info",
      summary: "information",
      detail: "data saved as a draft",
    });
    this.index = this.index === 2 ? 0 : this.index + 1;
  }
  openPrev() {
    this.index = this.index === 0 ? 2 : this.index - 1;
  }

  openNextUpdate() {
    this.messageService.add({
      severity: "info",
      summary: "information",
      detail: "data saved as a draft",
    });
    this.newindex = this.newindex === 2 ? 0 : this.newindex + 1;
  }

  openPrevUpdate() {
    this.newindex = this.newindex === 0 ? 2 : this.newindex - 1;
  }

  clientNamePattern = "^[a-zA-Z ]{3,255}$";
  firstNamePattern = "^[a-zA-Z ]{3,15}$";
  lastNamePattern = "^[a-zA-Z ]{3,15}$";
  rfpNamePattern = "^[a-zA-Z ]{3,255}$";
  projectNamePattern = "^[a-zA-Z ]{3,255}$";
  projectCodePattern = "^[a-zA-Z0-9 ]{3,15}$";
  mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  desciptionPattern = "^[a-zA-Z0-9@!#$%&*-_ ]{3,255}$";
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  
  constructor(
    private vendorService: VendorMngServiceService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
    this.industryNames = [
      { industryName: "IT" },
      { industryName: "Manufacturing" },
      { industryName: "Commerse" },
      { industryName: "Construction" },
      { industryName: "Financial" },
    ];

    this.businessUsers = [
      { name: "Ananya" },
      { name: "Sangam" },
      { name: "Rahul" },
      { name: "Ashutosh" },
    ];

    this.managers = [
      { name: "Akshay Shinde" },
      { name: "Siddesh Kadus" },
      { name: "Shivraj Godle" },
      { name: "Tasdiq Shaikh" },
    ];

    this.partners = [
      { name: "Shubham Saykar" },
      { name: "Upendra Kale" },
      { name: "Krushna Kure" },
      { name: "Abhishek Gawade" },
    ];
  }

  form!: FormGroup;
  projectId:string="";

  ngOnInit(): void {
    this.clientForm1 = new FormGroup({
      clientName: new FormControl("", [
        Validators.required,
        Validators.pattern(this.clientNamePattern),
      ]),
      industry: new FormControl(""),
      businessOwner:new FormControl('passengerForm'),

      firstname: new FormControl("", [
        Validators.required,
        Validators.pattern(this.firstNamePattern),
      ]),
      lastname: new FormControl("", [
        Validators.required,
        Validators.pattern(this.lastNamePattern),
      ]),
      email: new FormControl("", [
        Validators.required,
        Validators.pattern(this.emailPattern),
      ]),
      // })

      // this.clientForm2 = new FormGroup({
      projectName: new FormControl("", [
        Validators.required,
        Validators.pattern(this.projectNamePattern),
      ]),
      projectCode: new FormControl("", [
        Validators.required,
        Validators.pattern(this.projectCodePattern),
      ]),
      partnerName: new FormControl(""),
      managerName: new FormControl(""),
      businessuser: new FormControl(""),
      // })

      // this.clientForm3 = new FormGroup({
      rfpName: new FormControl("", [
        Validators.required,
        Validators.pattern(this.rfpNamePattern),
      ]),
      description: new FormControl("", [
        Validators.required,
        Validators.pattern(this.desciptionPattern),
      ]),
      date: new FormControl(""),
      document: new FormControl(""),
    });

    // to fetch all clients info
    this.vendorService.getClients().subscribe(
      (data: any) => {
        // console.log(data);

        this.clientInfo = data;
        console.log(this.clientInfo,"all data");
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
  }

  display() {
    console.log(this.selectedPartner);
  }
  passengerForm = [
    {
      firstname: "",
      lastname: "",
      email: "",
    },
  ];
  addFormBOx: boolean = false;
  addForm() {
    this.addFormBOx = true;
    this.passengerForm.push({
      firstname: "",
      lastname: "",
      email: "",
    });
  }

  removeForm() {
    this.passengerForm.pop();
  }

  onClickCancel() {
    this.newProjectDialog = false;
    this.editProjectDialog = false;
    this.clientForm1.reset();
    this.clientForm2.reset();
    this.clientForm3.reset();
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }
  
  Download(id:string) {
    this.vendorService.download(id).subscribe(
      (event: Blob) => {
        saveAs(event, id);
        console.log("file downloaded");
        this.messageService.add({
          severity: "success",
          summary: "Download complete",
          detail: "File has been downloaded and store in 'Downloads' ",
        });
      },
      (error: HttpErrorResponse) => {
        console.log("error");
      }
    );
  }

  onUpload(projectId:string) {
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);
      if (file) {
        this.currentFile = file;
        this.vendorService.uploadDoc(this.currentFile,projectId).subscribe(
          (data: any) => {
            this.ngOnInit();
            console.log(projectId,"doc uploaded");
          },
          (error: HttpErrorResponse) => {
            console.log("error");
          }
        );
      }
    }
  }

  onClickAddProject() {
    this.newProjectDialog = true;
  }

  //           <!-- save button  for client-->
  onClickSaveClient() {
    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.clientForm1.value.document = file.name;
        // this.clientForm.value.industry = this.selectedIndustry;
        // this.clientForm.value.date = Date.now();
        // this.clientForm.value.partner = this.selectedPartner;
        // this.clientForm.value.manager = this.selectedManager;
        // this.clientForm.value.businessUser = this.selectedBusinessUserName;
        this.clientForm1.value.businessOwner=this.passengerForm;
        // this.clientForm3.value.document = file.name;

        console.log(JSON.stringify(this.clientForm1.value)," json data");
        // const AllData=this.clientForm1.value+this.clientForm2.value+this.clientForm3.value;
        // console.log(AllData," all data");
        this.vendorService.addClient(this.clientForm1.value).subscribe(
          (data: any) => {
            this.projectId=data.projectid;
            console.log(data," data of project");
            this.newProjectDialog = false;
            this.ngOnInit();
            this.messageService.add({
              severity: "success",
              summary: "Successfull",
              detail: "client data saved successfully",
            });
            console.log(JSON.stringify(data)," akkkiiii");
            this.onUpload(this.projectId);

            this.newProjectDialog = false;
            this.ngOnInit();
          },
          (error: HttpErrorResponse) => {
            this.messageService.add({
              severity: "error",
              summary: "Unsuccessfull",
              detail: "Error while adding user",
            });
            console.log(JSON.stringify(this.clientForm1.value)," akshay");

            this.newProjectDialog = false;
            this.ngOnInit();
          }
        );
      }

    }
  }

  passengerData: any[] = [];

  editProject(data: any) {
    // this.vendorService.getProjectById(id).subscribe(
    //   (data: any) => {
    //     this.newprojectData = data;
    //     this.passengerData = data.passengerForm1;
    //     this.editProjectDialog = true;
    //     console.log('project data ', this.newprojectData);
    //   },
    //   (error: HttpErrorResponse) => {
    //     alert(error);
    //   }
    // );
    this.newprojectData = data;
    console.log(this.newprojectData, " akshay");
    this.newprojectData.businessuser = this.newprojectData.businessuser[0]
    .substring(1, this.newprojectData.businessuser[0].length - 1).split(",");
 

    this.newprojectData.businessuser = this.newprojectData.businessuser.map(
      (element: any) => {
        return element.trim();
      }
    );
    
    //    this.passengerData = data.passengerForm1;

    this.editProjectDialog = true;
  }

  onClickUpdateClient() {

    console.log(this.newprojectData,"data to be updated");
    

    // this.passengerData.concat(this.passengerForm);
    // this.newprojectData.passengerForm=this.passengerData;
    this.confirmationService.confirm({
      message: "Are you sure that you want to edit this Project?",
      accept: () => {
        this.vendorService.updateProject(this.newprojectData).subscribe(
          (data: any) => {
            console.log("Project Details Updated" + data);
            this.messageService.add({
              severity: "success",
              summary: "Success",
              detail: "Project updated Successfully",
            });
            this.editProjectDialog = false;
            this.ngOnInit();
          },
          (error: HttpErrorResponse) => {
            this.messageService.add({
              severity: "cancle",
              summary: "Cancelled",
              detail: `${error}`,
            });
            this.editProjectDialog = false;
            this.ngOnInit();
          }
        );
      },

      reject: () => {
        this.messageService.add({
          severity: "warn",
          summary: "Cancelled",
          detail: "Project not updated",
        });
      },
    });
  }

  deleteProject() {
    this.confirmationService.confirm({
      message: "Are you sure that you want to Delete this Project?",
      accept: () => {
        for (let id of this.selectedId) {
          this.vendorService.deleteProject(id).subscribe(
            (data: any) => {
              this.messageService.add({
                severity: "success",
                summary: "Deleted",
                detail: "Project deleted successfully",
              });
            },
            (error: HttpErrorResponse) => {
              this.messageService.add({
                severity: "danger",
                summary: "Error",
                detail: "Something went wrong while deliting vendor",
              });
            }
          );
        }
        this.selectedId = [];
        this.ngOnInit();
      },
      reject: () => {
        this.messageService.add({
          severity: "warn",
          summary: "Cancelled",
          detail: "Project not deleted",
        });
      },
    });
  }

  // to save project details
  // onClickSaveProject() {
  //   this.projectForm.value.partner = this.selectedPartner;
  //   this.projectForm.value.manager = this.selectedManager;
  //   this.projectForm.value.businessUser = this.selectedBusinessUserName;
  //   this.projectData = this.projectForm.value;
  //   this.vendorService.addProject(this.projectForm.value).subscribe(
  //     (data: any) => {
  //       console.log(data);

  //       this.messageService.add({
  //         severity: 'success',
  //         summary: 'Successfull',
  //         detail: 'Project data saved successfully',
  //       });
  //     },
  //     (error: HttpErrorResponse) => {
  //       this.messageService.add({
  //         severity: 'danger',
  //         summary: 'Error',
  //         detail: 'Something went wrong while saving Project info',
  //       });
  //     }
  //   );
  // }

  // to save RFP Details
  // onClickSaveRfp() {
  //   this.rfpForm.value.date = Date.now();
  //   this.vendorService.addRfp(this.rfpInfo).subscribe(
  //     (data: any) => {
  //       console.log(data);
  //       this.newProjectDialog = false;
  //       this.ngOnInit();
  //       this.messageService.add({
  //         severity: 'success',
  //         summary: 'Successfull',
  //         detail: 'Rfp details saved successfully',
  //       });
  //     },
  //     (error: HttpErrorResponse) => {
  //       this.messageService.add({
  //         severity: 'danger',
  //         summary: 'Error',
  //         detail: 'Something went wrong while saving Rfp info',
  //       });
  //     }
  //   );
  // }

  SaveDraftProjectForm1() {
    console.log(this.clientForm1.value);
  }
  SaveDraftProjectForm2() {
    console.log(this.clientForm1.value);
  }
}
