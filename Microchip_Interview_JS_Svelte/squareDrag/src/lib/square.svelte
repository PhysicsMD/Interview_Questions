<script>
  import { trusted } from "svelte/internal";

  var boxX, boxY, boxXOffset, boxYOffset
	boxX = boxY = boxXOffset = boxYOffset = 0
  let boxWidth = 50
  var direction = [0,0]
  let timerID = null
  var isMouseFollowed = false
  let isbound = false
  let isDVDbounce = false
  let isThrow = false
  let oldPos = [0,0]
  let newPos = [0,0]
  const mouseIsMoving = (e) => {
    if(isMouseFollowed){
      if(isbound || isThrow){
        if(e.clientX+boxXOffset < 0){
          boxX = 0
        }
        else if(e.clientX + boxXOffset+boxWidth > window.innerWidth){
          boxX = window.innerWidth - boxWidth
        }
        else{
          boxX = e.clientX + boxXOffset
        }
        if(e.clientY+boxYOffset < 0){
          boxY = 0
        }
        else if(e.clientY+boxWidth+boxYOffset > window.innerHeight){
          boxY = window.innerHeight - boxWidth
        }
        else{
          boxY = e.clientY + boxYOffset
        }
        // for direction
        oldPos = newPos
        newPos = [boxX, boxY]
      }
      else {
        boxX = e.clientX + boxXOffset
        boxY = e.clientY + boxYOffset
      }
    }
  }
  const followmouse = (e) => {
    if(isDVDbounce){
      //do nothing
    }
    else{
      isMouseFollowed = true
		  // This adds an offset for where the mouse clicked on the box
		  boxXOffset = boxX - e.clientX
		  boxYOffset = boxY - e.clientY
      if(isThrow){
        clearInterval(timerID)
        oldPos = newPos = [boxX, boxY]
      }
    }
  }
  const thrown = () => {
    boxX += direction[0]
    boxY += direction[1]
    let friction = .95
    let gravity = .75
    if(boxX+boxWidth >= window.innerWidth || boxX <= 0){
      direction[0] *= -1
    }
    if(Math.abs(direction[0]) < .001){
      // prevent over calculation
      direction[0] = 0
    }
    direction[0] *= friction

    if(boxY+boxWidth >= window.innerHeight){
      direction[1] = 0
      boxY = window.innerHeight-boxWidth
    }
    direction[1] += gravity
  }
  const unfollowmouse = (e) => {
    isMouseFollowed = false
    if(isThrow){
      direction = [newPos[0] - oldPos[0], newPos[1] - oldPos[1]]
      timerID = setInterval(thrown, 15)
    }
  }
  const bounce = () => {
    if(direction[0] == 0 && direction[1] == 0){
      direction[0] = (Math.floor(Math.random()*5)+1)
      direction[1] = (Math.floor(Math.random()*5)+1)
    }
    if(boxX+boxWidth > window.innerWidth){
      direction[0] *= -1
    }
    if(boxX < 0){
      direction[0] *= -1
    }
    if(boxY+boxWidth > window.innerHeight){
      direction[1] *= -1
    }
    if(boxY < 0){
      direction[1] *= -1
    }
    boxX += direction[0]
    boxY += direction[1]
  }
  const selector = (e) => {
    if(e.target.value == "reset"){
      boxX = 0
      boxY = 0
    }
    isbound = "Bind" == e.target.value
    isDVDbounce = "dvdb" == e.target.value
    if(isDVDbounce){
      timerID = setInterval(bounce, 15)
    }
    else {
      clearInterval(timerID)
      direction = [0,0]
    }
    isThrow = "throw" == e.target.value
  }
  document.addEventListener("mousemove", mouseIsMoving)
</script>

<style>
  .coordinateTab {
  z-index: 2;
  position: absolute;
	color: white;
  background-color: #999;
  border-radius: 50px;
  width: auto;
  padding: 1px 7px 2px 7px;
  bottom: 10px;
  right: 10px;
}

.optionsTab {
  z-index: 2;
  position: absolute;
	color: white;
  background-color: #999;
  border-radius: 15px 0px 0px 15px;
  width: 50px;
  height: 50px;
  top: 10px;
  right: 5px;
  box-shadow: 5px 0px 0px 0px #999;
  transition: all 1s ease-in-out;
  overflow: hidden;
}

.optionsTab div:not(:first-child) {
  width: 400px;
  padding-left:200px;
  transition-delay: 1s;
}

.optionsTab:hover{
  transition: all 1s ease-in-out;
  width: 150px;
  height: auto;
  padding-top: 5px;
  text-align: left;
}

.optionsTab div:first-child {
  visibility: visible;
  font-size: 50px;
	line-height:15px;
  /* padding top is 17px local and 12px in repl*/
  padding: 12px 0px 15px 10px;
}

.optionsTab:hover div:not(:first-child) {
  visibility: visible;
  transition-delay: 1s;
  padding-left:0px;
}

.optionsTab:hover div:first-child {
  display:none;
  transition-duration: 1s;
}

.square {
  position: absolute;
  background-color: black;
  aspect-ratio: 1;
}
	label {
		display: inline-block;
	}
</style>

<div class="square" on:mousedown={followmouse} on:mouseup={unfollowmouse} style="width: {boxWidth}px; top: {boxY}px; left: {boxX}px"></div>

<div class="coordinateTab">
  X: {Math.round(boxX)} , Y: {Math.round(boxY)}
</div>

<div class="optionsTab">
  <div>></div>
  <div>
    <form>
    <fieldset id="options" style="border: none;" on:input={selector}>
      <input type="radio" id="reset" name="options" value="reset">
      <label for="reset"> Reset</label><br>
    
    <input type="radio" id="unBind" name="options" value="unBind" checked>
    <label for="unBind"> Unbound?</label><br>

    <input type="radio" id="Bind" name="options" value="Bind">
    <label for="Bind"> Bound?</label><br>

    <input type="radio" id="dvdb" name="options" value="dvdb">
    <label for="dvdb"> DVD Bounce</label><br>

    <input type="radio" id="throw" name="options" value="throw">
    <label for="throw"> Throw</label><br>
    </fieldset>
  </form>
  </div>
</div>
