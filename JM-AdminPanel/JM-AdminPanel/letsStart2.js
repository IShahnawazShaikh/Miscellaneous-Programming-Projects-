firebase.auth().onAuthStateChanged(function(user){
   if(user){  //1
           if(user.displayName==null){ //3
         alert('name==null')
          var user=firebase.auth().currentUser;
          var url="";
       var fileButton=document.getElementById('fileButton');
      fileButton.addEventListener('change', function(e){
                  var file = e.target.files[0];
                  var storageRef = firebase.storage().ref("Admin/"+file.name);
                  var uploadTask=storageRef.put(file);
                 uploadTask.on('state_changed', function(snapshot){
  var progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
  console.log('Upload is ' + progress + '% done');
    document.getElementById('progress').innerHTML=progress;
  switch (snapshot.state) {
    case firebase.storage.TaskState.PAUSED: // or 'paused'
      console.log('Upload is paused');
      break;
    case firebase.storage.TaskState.RUNNING: // or 'running'
      console.log('Upload is running');
      break;
  }
}, function(error) {
}, function() {
  uploadTask.snapshot.ref.getDownloadURL().then(function(downloadURL) {
    console.log('File available at', downloadURL);
      url=downloadURL;
     alert("URL=> "+url);
     progress="";
    });
  });
 }); 
    document.getElementById('setInfo').addEventListener('click',e=>{ 
    var name=document.getElementById('name').value;  
    alert(name);
    alert(url);
    user.updateProfile({
     displayName: name,
     photoURL: url
    }).then(function() {
     alert('User Profile Updated Successfully '+name);
     document.getElementById('name').value="";
     document.getElementById('fileButton').value="";
      document.getElementById('progress').innerHTML=progress;
     document.getElementById('authEmail').value="";
     document.getElementById('authPassword').value="";
        document.getElementById("detailBox").style.display='none';
        document.getElementById('SignInBox').style.display='none';
        //firebase.auth().signOut();
        //window.location.reload();

    }).catch(function(error) {
    alert(error)
   });
  });
         var email=user.email;
        } //3
           else{ //4            
                    document.getElementById('main').style.background='#f1f1f1';
                    document.getElementById('SignInBox').style.display='none';
                    document.getElementById('DashBoardSection').style.display='block';
                    var myNode = document.getElementById("adminPart");
                    while (myNode.firstChild) {
                    myNode.removeChild(myNode.firstChild);
                  }
                   var userName = document.createElement('p');
                   var userEmail= document.createElement('p');
                   var userUid= document.createElement('p');
                   var logOutDashBoard= document.createElement('p');
                   var userImg = document.createElement('img');
                  userName.innerHTML =user.displayName;
                  userEmail.innerHTML =user.email;
                  userUid.innerHTML =user.uid;
                  logOutDashBoard.innerHTML ='Logout';
                  userImg.src =user.photoURL;
                  document.getElementById('adminPart').appendChild(userImg);
                  document.getElementById('adminPart').appendChild(userName);
                  document.getElementById('adminPart').appendChild(userEmail);
                  document.getElementById('adminPart').appendChild(userUid);
                  document.getElementById('adminPart').appendChild(logOutDashBoard);
                  document.getElementById('contentPart').style.display='block';
                  logOutDashBoard.onclick=function(){
                  document.getElementById('email').value="";
                  document.getElementById('password').value="";
                  document.getElementById('DashBoardSection').style.display='none';
                   document.getElementById('main').style.background='aliceblue';
                   firebase.auth().signOut();
                   window.location.reload();
                } 
           } //4
   } //1
   else{  //2
       document.getElementById('SignInBox').style.display='block';
    } //2
});
function signIn(){
  var email=document.getElementById('email').value;
  var password=document.getElementById('password').value;
  firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
  var errorCode = error.code;
  var errorMessage = error.message;
  window.alert("Error"+errorMessage);
  });
}
function signUp(){
alert('sign Up kar Rha hu')
var email=document.getElementById('authEmail').value;
var password=document.getElementById('authPassword').value;  
firebase.auth().createUserWithEmailAndPassword(email, password).catch(function(error) {
 if(error){ 
  var errorCode = error.code;
  var errorMessage = error.message;
  window.alert("Error"+errorMessage);
 }
 else{
    alert('You have registered');
 }
  });
    document.getElementById("detailBox").style.display='block';
}

$(document).ready(function(){
	   
   $('.button').click(function(){
      if($(this).val()=='auth'){
      	   $('#Rguards').css("display","none");
      	   $('#Rstudents').css("display","none");
      	   $('#viewDetails').css("display","none");
      	   $('#authenticate').css("display","block");
           $('#rs1').css("background-color","aquamarine");
           $('#rg1').css("background-color","aquamarine");
           $('#auth1').css("background-color","greenyellow");
           $('#view1').css("background-color","aquamarine");
      }

     if($(this).val()=="rg"){
           $('#Rstudents').css("display","none");
           $('#viewDetails').css("display","none");
           $('#authenticate').css("display","none");          
          $('#Rguards').css("display","block");
          $("#rs1").css("background-color","aquamarine");
           $("#rg1").css("background-color","greenyellow");
           $("#auth1").css("background-color","aquamarine");
           $("#view1").css("background-color","aquamarine");

     }
     if($(this).val()=="rs"){
           $('#viewDetails').css("display","none");
           $('#authenticate').css("display","none");          
           $('#Rguards').css("display","none");
           $('#Rstudents').css("display","block");
           $("#rs1").css("background-color","greenyellow");
           $("#rg1").css("background-color","aquamarine");
           $("#auth1").css("background-color","aquamarine");
           $("#view1").css("background-color","aquamarine");
           var fileButton=document.getElementById('fileButton2');
            var uploader=document.getElementById('uploader');
              fileButton.addEventListener('change', function(e){
                  var file = e.target.files[0];
                  var storageRef = firebase.storage().ref("Student/Student Images"+file.name);
                  var uploadTask=storageRef.put(file);
                 uploadTask.on('state_changed', function(snapshot){
  var progress2 = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
  console.log('Upload is ' + progress2 + '% done');
    document.getElementById('progress2').innerHTML=progress2;
  switch (snapshot.state) {
    case firebase.storage.TaskState.PAUSED: // or 'paused'
      console.log('Upload is paused');
      break;
    case firebase.storage.TaskState.RUNNING: // or 'running'
      console.log('Upload is running');
      break;
  }
}, function(error) {
}, function() {
  uploadTask.snapshot.ref.getDownloadURL().then(function(downloadURL) {
    console.log('File available at', downloadURL);
     this.URL=downloadURL;
     progress2="";
     });
   });
 });
}
  if($(this).val()=="view"){
           $('#Rstudents').css("display","none");
           $('#authenticate').css("display","none");          
           $('#Rguards').css("display","none");
           $('#viewDetails').css("display","block");
           $("#rs1").css("background-color","aquamarine");
           $("#rg1").css("background-color","aquamarine");
           $("#auth1").css("background-color","aquamarine");
           $("#view1").css("background-color","greenyellow");
           var counter=0;
           getDetails=[];
           var rootRef=firebase.database().ref().child('Students');
            $('.studentIDs').empty();
           rootRef.on('child_added',snap =>{
            counter++;         
             $('.studentIDs').append("<span>"+counter+". <p class='completeDetails'>"+snap.key+"</p><p class='completeDetailsName'>&nbsp("+snap.child('first_Name').val()+"&nbsp"+snap.child('last_Name').val()+")</p>")
             var key=snap.key;
             var fname=snap.child('first_Name').val();
             var lname=snap.child('last_Name').val();
             var email=snap.child('email').val();
             var number=snap.child('number').val();
             var pic=snap.child('pic').val();
             var expire=snap.child('expireDate').val();
             var issue=snap.child('issueDate').val();
             var faculty=snap.child('faculty').val();
             var course=snap.child('course').val();
            getDetails[counter-1]={
              key:key,
              fname:fname,
              lname:lname,
              email:email,
              number:number,
              pic:pic,
              course:course,
              faculty:faculty,
              issue:issue,
              expire:expire
           }  
      });
     $(document).on("click", ".completeDetails", function(event){
              alert($(this).text());  //getting text of
               for(var i=0;i<getDetails.length;i++){
                   if($(this).text()==getDetails[i].key){
                      $('.studentData').css('display','block');
                      $('.studentIDs').find('span').css('background-color','white');
                       $(this).parent('span').css('background-color','greenyellow');
                     $('.studentData').html("<img src="+getDetails[i].pic+"><p>"+getDetails[i].fname+" "+getDetails[i].lname+"</p><p>Faculty: "+getDetails[i].faculty+"</p><p>Course: "+getDetails[i].course+"</p><p>Email: "+getDetails[i].email+"</p><p>Number: "+getDetails[i].number+"</p><p>Joining Date: "+getDetails[i].issue+"</p><p>Expire Date: "+getDetails[i].expire+"</p>");            
                   }
               }
         });
     }
   })
   $(".signUp").click(function(){
       $("#detailBox").css("display","block");
   })
})
function writeGuard(){
  var id=document.getElementById('GId').value;
  var Fname=document.getElementById('GFname').value;
  var Lname=document.getElementById('GLname').value;
  var Email=document.getElementById('GEmail').value;
  var Number=document.getElementById('GNumber').value;
  var Password=document.getElementById('GPassword').value;
     var fire= firebase.database().ref();
      fire.child("GuardDB").child(id).set({
        first_Name:Fname,
        last_Name:Lname,
        email:Email,
        number:Number,
        password: Password
      });
     alert("Inserted successfully");
     document.getElementById('GId').value="";
     document.getElementById('GFname').value="";
     document.getElementById('GLname').value="";
     document.getElementById('GEmail').value="";
     document.getElementById('GNumber').value="";
     document.getElementById('GPassword').value="";   
 }
  function writeStudent(){
  var Id=document.getElementById('SId').value;
  var Fname=document.getElementById('SFname').value;
  var Lname=document.getElementById('SLname').value;
  var Email=document.getElementById('SEmail').value;
  var Number=document.getElementById('SNumber').value;
  var Issue=document.getElementById('SIsueDate').value;
  var Expire=document.getElementById('SExpireDate').value;
  var Faculty=document.getElementById('SFaculty').value;
  var Course=document.getElementById('SCourse').value;
     var fire= firebase.database().ref();
      fire.child("Students").child(Id).set({
        first_Name:Fname,
        last_Name:Lname,
        email:Email,
        number:Number,
        faculty:Faculty,
        course:Course,
        issueDate:Issue,
        expireDate:Expire,
        pic:URL,
        iid:Id,
        Active:0
      });
     alert("Inserted successfully");
     document.getElementById('SId').value="";
     document.getElementById('SFname').value="";
     document.getElementById('SLname').value="";
     document.getElementById('SEmail').value="";
     document.getElementById('SNumber').value="";
     document.getElementById('SIsueDate').value="";
     document.getElementById('SExpireDate').value="";
     document.getElementById('SFaculty').value="";
     document.getElementById('SCourse').value="";
     document.getElementById('fileButton2').value="";
    document.getElementById('progress2').innerHTML=progress2;
  }
