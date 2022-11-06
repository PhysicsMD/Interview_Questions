<script>
  import { trusted } from "svelte/internal";

  var boxX, boxY, boxXOffset, boxYOffset
	boxX = boxY = boxXOffset = boxYOffset = 0
  let boxWidth = 50
  var isMouseFollowed = false
  const mouseIsMoving = (e) => {
    if(isMouseFollowed){
      boxX = e.clientX + boxXOffset
      boxY = e.clientY + boxYOffset
    }
  }
  const followmouse = (e) => {
    isMouseFollowed = true
		// This adds an offset for where the mouse clicked on the box
		boxXOffset = boxX - e.clientX
		boxYOffset = boxY - e.clientY
  }
  const unfollowmouse = (e) => {
    isMouseFollowed = false
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

.square {
  position: absolute;
  background-color: black;
  aspect-ratio: 1;
}
</style>

<div class="square" on:mousedown={followmouse} on:mouseup={unfollowmouse} style="width: {boxWidth}px; top: {boxY}px; left: {boxX}px"></div>

<div class="coordinateTab">
  X: {boxX} , Y: {boxY}
</div>
